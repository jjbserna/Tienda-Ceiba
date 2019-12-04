var app = angular.module('reportingApp', []);

//<editor-fold desc="global helpers">

var isValueAnArray = function (val) {
    return Array.isArray(val);
};

var getSpec = function (str) {
    var describes = str.split('|');
    return describes[describes.length - 1];
};
var checkIfShouldDisplaySpecName = function (prevItem, item) {
    if (!prevItem) {
        item.displaySpecName = true;
    } else if (getSpec(item.description) !== getSpec(prevItem.description)) {
        item.displaySpecName = true;
    }
};

var getParent = function (str) {
    var arr = str.split('|');
    str = "";
    for (var i = arr.length - 2; i > 0; i--) {
        str += arr[i] + " > ";
    }
    return str.slice(0, -3);
};

var getShortDescription = function (str) {
    return str.split('|')[0];
};

var countLogMessages = function (item) {
    if ((!item.logWarnings || !item.logErrors) && item.browserLogs && item.browserLogs.length > 0) {
        item.logWarnings = 0;
        item.logErrors = 0;
        for (var logNumber = 0; logNumber < item.browserLogs.length; logNumber++) {
            var logEntry = item.browserLogs[logNumber];
            if (logEntry.level === 'SEVERE') {
                item.logErrors++;
            }
            if (logEntry.level === 'WARNING') {
                item.logWarnings++;
            }
        }
    }
};

var convertTimestamp = function (timestamp) {
    var d = new Date(timestamp),
        yyyy = d.getFullYear(),
        mm = ('0' + (d.getMonth() + 1)).slice(-2),
        dd = ('0' + d.getDate()).slice(-2),
        hh = d.getHours(),
        h = hh,
        min = ('0' + d.getMinutes()).slice(-2),
        ampm = 'AM',
        time;

    if (hh > 12) {
        h = hh - 12;
        ampm = 'PM';
    } else if (hh === 12) {
        h = 12;
        ampm = 'PM';
    } else if (hh === 0) {
        h = 12;
    }

    // ie: 2013-02-18, 8:35 AM
    time = yyyy + '-' + mm + '-' + dd + ', ' + h + ':' + min + ' ' + ampm;

    return time;
};

var defaultSortFunction = function sortFunction(a, b) {
    if (a.sessionId < b.sessionId) {
        return -1;
    } else if (a.sessionId > b.sessionId) {
        return 1;
    }

    if (a.timestamp < b.timestamp) {
        return -1;
    } else if (a.timestamp > b.timestamp) {
        return 1;
    }

    return 0;
};

//</editor-fold>

app.controller('ScreenshotReportController', ['$scope', '$http', 'TitleService', function ($scope, $http, titleService) {
    var that = this;
    var clientDefaults = {};

    $scope.searchSettings = Object.assign({
        description: '',
        allselected: true,
        passed: true,
        failed: true,
        pending: true,
        withLog: true
    }, clientDefaults.searchSettings || {}); // enable customisation of search settings on first page hit

    this.warningTime = 1400;
    this.dangerTime = 1900;
    this.totalDurationFormat = clientDefaults.totalDurationFormat;
    this.showTotalDurationIn = clientDefaults.showTotalDurationIn;

    var initialColumnSettings = clientDefaults.columnSettings; // enable customisation of visible columns on first page hit
    if (initialColumnSettings) {
        if (initialColumnSettings.displayTime !== undefined) {
            // initial settings have be inverted because the html bindings are inverted (e.g. !ctrl.displayTime)
            this.displayTime = !initialColumnSettings.displayTime;
        }
        if (initialColumnSettings.displayBrowser !== undefined) {
            this.displayBrowser = !initialColumnSettings.displayBrowser; // same as above
        }
        if (initialColumnSettings.displaySessionId !== undefined) {
            this.displaySessionId = !initialColumnSettings.displaySessionId; // same as above
        }
        if (initialColumnSettings.displayOS !== undefined) {
            this.displayOS = !initialColumnSettings.displayOS; // same as above
        }
        if (initialColumnSettings.inlineScreenshots !== undefined) {
            this.inlineScreenshots = initialColumnSettings.inlineScreenshots; // this setting does not have to be inverted
        } else {
            this.inlineScreenshots = false;
        }
        if (initialColumnSettings.warningTime) {
            this.warningTime = initialColumnSettings.warningTime;
        }
        if (initialColumnSettings.dangerTime) {
            this.dangerTime = initialColumnSettings.dangerTime;
        }
    }


    this.chooseAllTypes = function () {
        var value = true;
        $scope.searchSettings.allselected = !$scope.searchSettings.allselected;
        if (!$scope.searchSettings.allselected) {
            value = false;
        }

        $scope.searchSettings.passed = value;
        $scope.searchSettings.failed = value;
        $scope.searchSettings.pending = value;
        $scope.searchSettings.withLog = value;
    };

    this.isValueAnArray = function (val) {
        return isValueAnArray(val);
    };

    this.getParent = function (str) {
        return getParent(str);
    };

    this.getSpec = function (str) {
        return getSpec(str);
    };

    this.getShortDescription = function (str) {
        return getShortDescription(str);
    };
    this.hasNextScreenshot = function (index) {
        var old = index;
        return old !== this.getNextScreenshotIdx(index);
    };

    this.hasPreviousScreenshot = function (index) {
        var old = index;
        return old !== this.getPreviousScreenshotIdx(index);
    };
    this.getNextScreenshotIdx = function (index) {
        var next = index;
        var hit = false;
        while (next + 2 < this.results.length) {
            next++;
            if (this.results[next].screenShotFile && !this.results[next].pending) {
                hit = true;
                break;
            }
        }
        return hit ? next : index;
    };

    this.getPreviousScreenshotIdx = function (index) {
        var prev = index;
        var hit = false;
        while (prev > 0) {
            prev--;
            if (this.results[prev].screenShotFile && !this.results[prev].pending) {
                hit = true;
                break;
            }
        }
        return hit ? prev : index;
    };

    this.convertTimestamp = convertTimestamp;


    this.round = function (number, roundVal) {
        return (parseFloat(number) / 1000).toFixed(roundVal);
    };


    this.passCount = function () {
        var passCount = 0;
        for (var i in this.results) {
            var result = this.results[i];
            if (result.passed) {
                passCount++;
            }
        }
        return passCount;
    };


    this.pendingCount = function () {
        var pendingCount = 0;
        for (var i in this.results) {
            var result = this.results[i];
            if (result.pending) {
                pendingCount++;
            }
        }
        return pendingCount;
    };

    this.failCount = function () {
        var failCount = 0;
        for (var i in this.results) {
            var result = this.results[i];
            if (!result.passed && !result.pending) {
                failCount++;
            }
        }
        return failCount;
    };

    this.totalDuration = function () {
        var sum = 0;
        for (var i in this.results) {
            var result = this.results[i];
            if (result.duration) {
                sum += result.duration;
            }
        }
        return sum;
    };

    this.passPerc = function () {
        return (this.passCount() / this.totalCount()) * 100;
    };
    this.pendingPerc = function () {
        return (this.pendingCount() / this.totalCount()) * 100;
    };
    this.failPerc = function () {
        return (this.failCount() / this.totalCount()) * 100;
    };
    this.totalCount = function () {
        return this.passCount() + this.failCount() + this.pendingCount();
    };


    var results = [
    {
        "description": "debe tener titulo|Prueba flujo principal",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "004d0016-0016-0016-0068-005900db004f.png",
        "timestamp": 1575465922451,
        "duration": 2792
    },
    {
        "description": "crear Producto|Boton crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "008e003e-003a-00e6-0062-009400230021.png",
        "timestamp": 1575465926321,
        "duration": 3174
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "005200ee-0066-003c-0028-008c00470012.png",
        "timestamp": 1575465929959,
        "duration": 11540
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "000a0099-007d-0098-00c6-005d00a9005e.png",
        "timestamp": 1575465941896,
        "duration": 10420
    },
    {
        "description": "crear cliente|Boton crear cliente",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575465954430,
                "type": ""
            }
        ],
        "screenShotFile": "008900f9-00a3-0082-00ef-005500d700d6.png",
        "timestamp": 1575465952720,
        "duration": 1766
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575465955309,
                "type": ""
            },
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575465956790,
                "type": ""
            }
        ],
        "screenShotFile": "000a009e-004c-00e3-00f8-0086006a0096.png",
        "timestamp": 1575465955079,
        "duration": 15842
    },
    {
        "description": "crear pedido|Boton crear pedido",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575465971495,
                "type": ""
            }
        ],
        "screenShotFile": "00260093-008f-000d-00fb-003f00fc00a8.png",
        "timestamp": 1575465971331,
        "duration": 2071
    },
    {
        "description": "Creacion nuevo pedido|Crear pedido",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedidoprenda/crear - Failed to load resource: the server responded with a status of 500 (Internal Server Error)",
                "timestamp": 1575465978442,
                "type": ""
            },
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedidoprenda/crear - Failed to load resource: the server responded with a status of 400 (Bad Request)",
                "timestamp": 1575465980732,
                "type": ""
            }
        ],
        "screenShotFile": "00550059-00b7-00de-008c-00e4004600d5.png",
        "timestamp": 1575465973928,
        "duration": 10350
    },
    {
        "description": "generar factura|Boton generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedidoprenda/crear - Failed to load resource: the server responded with a status of 500 (Internal Server Error)",
                "timestamp": 1575465984909,
                "type": ""
            },
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedidoprenda/crear - Failed to load resource: the server responded with a status of 400 (Bad Request)",
                "timestamp": 1575465984909,
                "type": ""
            }
        ],
        "screenShotFile": "00570030-0000-00f0-00e8-004700e9000d.png",
        "timestamp": 1575465984712,
        "duration": 1787
    },
    {
        "description": "Generacion de factura|Generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "3da1aa3873b17b8f7a61f6266fbe5a16",
        "instanceId": 17664,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "0067007a-001d-00dc-00dc-00c000ed000b.png",
        "timestamp": 1575465986975,
        "duration": 3182
    },
    {
        "description": "debe tener titulo|Prueba flujo principal",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00cd00b9-00e6-009e-0032-00cd00c90023.png",
        "timestamp": 1575466127507,
        "duration": 2167
    },
    {
        "description": "crear Producto|Boton crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00620046-00c8-006e-00df-005000410025.png",
        "timestamp": 1575466130244,
        "duration": 1678
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00380032-00a2-00e7-00fe-00cd003200d4.png",
        "timestamp": 1575466132429,
        "duration": 12545
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00c50087-0099-0008-0074-00ba00c900d4.png",
        "timestamp": 1575466145321,
        "duration": 10012
    },
    {
        "description": "crear cliente|Boton crear cliente",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575466157386,
                "type": ""
            }
        ],
        "screenShotFile": "003300ad-00c8-0006-00e8-004200c8006b.png",
        "timestamp": 1575466155674,
        "duration": 1742
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575466157961,
                "type": ""
            },
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575466159012,
                "type": ""
            }
        ],
        "screenShotFile": "00ba00ce-0077-00e2-003f-00bc00190022.png",
        "timestamp": 1575466157823,
        "duration": 15020
    },
    {
        "description": "crear pedido|Boton crear pedido",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575466173329,
                "type": ""
            }
        ],
        "screenShotFile": "001e0061-0001-000c-0037-0067003600f3.png",
        "timestamp": 1575466173202,
        "duration": 1400
    },
    {
        "description": "Creacion nuevo pedido|Crear pedido",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00a30002-00b6-00c8-007a-00ca00b10061.png",
        "timestamp": 1575466175004,
        "duration": 8330
    },
    {
        "description": "generar factura|Boton generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "005b009b-001f-000a-0097-00a300af00a0.png",
        "timestamp": 1575466183677,
        "duration": 1716
    },
    {
        "description": "Generacion de factura|Generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "5d2b6a318cfec16d0e3c99b9c000f503",
        "instanceId": 15504,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00d00035-000e-00d8-0041-003800f4003c.png",
        "timestamp": 1575466185796,
        "duration": 2860
    },
    {
        "description": "debe tener titulo|Prueba flujo principal",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "00ff0034-008b-00f9-00c0-00fb004e002f.png",
        "timestamp": 1575471303825,
        "duration": 3138
    },
    {
        "description": "crear Producto|Boton crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [],
        "screenShotFile": "0094002e-0070-0094-009a-0036008700df.png",
        "timestamp": 1575471307706,
        "duration": 2375
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": false,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": [
            "Failed: No element found using locator: by.buttonText(\"OK\")"
        ],
        "trace": [
            "NoSuchElementError: No element found using locator: by.buttonText(\"OK\")\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:814:27\n    at ManagedPromise.invokeCallback_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1376:14)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2927:27\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:668:7\n    at processTicksAndRejections (internal/process/task_queues.js:93:5)Error\n    at ElementArrayFinder.applyAction_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:459:27)\n    at ElementArrayFinder.<computed> [as click] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:91:29)\n    at ElementFinder.<computed> [as click] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:831:22)\n    at UserContext.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:39:38)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:112:25\n    at new ManagedPromise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1077:7)\n    at ControlFlow.promise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2505:12)\n    at schedulerExecute (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:95:18)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\nFrom: Task: Run it(\"Creacion nuevo producto\") in control flow\n    at UserContext.<anonymous> (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:94:19)\n    at attempt (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4297:26)\n    at QueueRunner.run (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4217:20)\n    at runNext (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4257:20)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4264:13\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4172:9\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:64:48\n    at ControlFlow.emit (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\events.js:62:21)\n    at ControlFlow.shutdown_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2674:10)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2599:53\nFrom asynchronous test: \nError\n    at Suite.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:23:5)\n    at addSpecsToSuite (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1107:25)\n    at Env.describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1074:7)\n    at describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4399:18)\n    at Object.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:22:1)\n    at Module._compile (internal/modules/cjs/loader.js:959:30)\n    at Object.Module._extensions..js (internal/modules/cjs/loader.js:995:10)\n    at Module.load (internal/modules/cjs/loader.js:815:32)\n    at Function.Module._load (internal/modules/cjs/loader.js:727:14)"
        ],
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/prenda/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471322465,
                "type": ""
            }
        ],
        "screenShotFile": "00370036-00e5-00a7-0060-00e1001300a1.png",
        "timestamp": 1575471310595,
        "duration": 11930
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": false,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": [
            "Failed: No element found using locator: by.buttonText(\"OK\")"
        ],
        "trace": [
            "NoSuchElementError: No element found using locator: by.buttonText(\"OK\")\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:814:27\n    at ManagedPromise.invokeCallback_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1376:14)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2927:27\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:668:7\n    at processTicksAndRejections (internal/process/task_queues.js:93:5)Error\n    at ElementArrayFinder.applyAction_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:459:27)\n    at ElementArrayFinder.<computed> [as click] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:91:29)\n    at ElementFinder.<computed> [as click] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:831:22)\n    at UserContext.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:62:38)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:112:25\n    at new ManagedPromise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1077:7)\n    at ControlFlow.promise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2505:12)\n    at schedulerExecute (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:95:18)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\nFrom: Task: Run it(\"Creacion nuevo producto\") in control flow\n    at UserContext.<anonymous> (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:94:19)\n    at attempt (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4297:26)\n    at QueueRunner.run (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4217:20)\n    at runNext (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4257:20)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4264:13\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4172:9\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:64:48\n    at ControlFlow.emit (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\events.js:62:21)\n    at ControlFlow.shutdown_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2674:10)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2599:53\nFrom asynchronous test: \nError\n    at Suite.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:46:5)\n    at addSpecsToSuite (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1107:25)\n    at Env.describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1074:7)\n    at describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4399:18)\n    at Object.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:45:1)\n    at Module._compile (internal/modules/cjs/loader.js:959:30)\n    at Object.Module._extensions..js (internal/modules/cjs/loader.js:995:10)\n    at Module.load (internal/modules/cjs/loader.js:815:32)\n    at Function.Module._load (internal/modules/cjs/loader.js:727:14)"
        ],
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/prenda/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471323198,
                "type": ""
            },
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/prenda/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471334270,
                "type": ""
            }
        ],
        "screenShotFile": "0083002b-0009-0031-0098-001d00070088.png",
        "timestamp": 1575471323046,
        "duration": 11242
    },
    {
        "description": "crear cliente|Boton crear cliente",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/prenda/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471334875,
                "type": ""
            },
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575471336499,
                "type": ""
            }
        ],
        "screenShotFile": "00a000bb-0092-0043-008f-0096003c001d.png",
        "timestamp": 1575471334681,
        "duration": 1895
    },
    {
        "description": "Creacion nuevo producto|Crear producto",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575471337286,
                "type": ""
            },
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575471338397,
                "type": ""
            },
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/cliente/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471352819,
                "type": ""
            }
        ],
        "screenShotFile": "00040058-001d-00e4-00f9-00d600600007.png",
        "timestamp": 1575471337081,
        "duration": 16858
    },
    {
        "description": "crear pedido|Boton crear pedido",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "WARNING",
                "message": "deprecation - '-webkit-appearance: listbox' for elements other than listbox select is deprecated and will be removed in M79, around December 2019. See https://www.chromestatus.com/features/5070237827334144 for more details.",
                "timestamp": 1575471354548,
                "type": ""
            },
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/cliente/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471354548,
                "type": ""
            }
        ],
        "screenShotFile": "005300f7-0079-0000-004d-002f00b70066.png",
        "timestamp": 1575471354393,
        "duration": 2085
    },
    {
        "description": "Creacion nuevo pedido|Crear pedido",
        "passed": false,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": [
            "Failed: No element found using locator: By(css selector, *[id=\"codigoPrenda\"])"
        ],
        "trace": [
            "NoSuchElementError: No element found using locator: By(css selector, *[id=\"codigoPrenda\"])\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:814:27\n    at ManagedPromise.invokeCallback_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1376:14)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2927:27\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:668:7\n    at processTicksAndRejections (internal/process/task_queues.js:93:5)Error\n    at ElementArrayFinder.applyAction_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:459:27)\n    at ElementArrayFinder.<computed> [as sendKeys] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:91:29)\n    at ElementFinder.<computed> [as sendKeys] (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\built\\element.js:831:22)\n    at UserContext.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:128:40)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:112:25\n    at new ManagedPromise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:1077:7)\n    at ControlFlow.promise (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2505:12)\n    at schedulerExecute (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:95:18)\n    at TaskQueue.execute_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3084:14)\n    at TaskQueue.executeNext_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:3067:27)\nFrom: Task: Run it(\"Creacion nuevo pedido\") in control flow\n    at UserContext.<anonymous> (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:94:19)\n    at attempt (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4297:26)\n    at QueueRunner.run (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4217:20)\n    at runNext (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4257:20)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4264:13\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4172:9\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasminewd2\\index.js:64:48\n    at ControlFlow.emit (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\events.js:62:21)\n    at ControlFlow.shutdown_ (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2674:10)\n    at C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\selenium-webdriver\\lib\\promise.js:2599:53\nFrom asynchronous test: \nError\n    at Suite.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:123:5)\n    at addSpecsToSuite (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1107:25)\n    at Env.describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:1074:7)\n    at describe (C:\\Users\\jeison.barbosa\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\jasmine-core\\lib\\jasmine-core\\jasmine.js:4399:18)\n    at Object.<anonymous> (D:\\GIT\\Tienda-ClienteFront\\protactor\\spec.js:122:1)\n    at Module._compile (internal/modules/cjs/loader.js:959:30)\n    at Object.Module._extensions..js (internal/modules/cjs/loader.js:995:10)\n    at Module.load (internal/modules/cjs/loader.js:815:32)\n    at Function.Module._load (internal/modules/cjs/loader.js:727:14)"
        ],
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedido/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471361425,
                "type": ""
            }
        ],
        "screenShotFile": "003300fb-003a-0026-00be-000c00030074.png",
        "timestamp": 1575471357000,
        "duration": 4459
    },
    {
        "description": "generar factura|Boton generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedido/crear - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471362100,
                "type": ""
            }
        ],
        "screenShotFile": "00d000f8-0021-0015-00ca-005600980024.png",
        "timestamp": 1575471361934,
        "duration": 1888
    },
    {
        "description": "Generacion de factura|Generar factura",
        "passed": true,
        "pending": false,
        "os": "windows nt",
        "sessionId": "dffd8213f5c463b6f85504c6c1e37ab1",
        "instanceId": 20400,
        "browser": {
            "name": "chrome",
            "version": "78.0.3904.108"
        },
        "message": "Passed.",
        "trace": "",
        "browserLogs": [
            {
                "level": "SEVERE",
                "message": "http://localhost:4200/api/pedidoprenda/99 - Failed to load resource: the server responded with a status of 504 (Gateway Timeout)",
                "timestamp": 1575471367861,
                "type": ""
            }
        ],
        "screenShotFile": "00f70038-0072-0062-00e6-00b400e500c3.png",
        "timestamp": 1575471364244,
        "duration": 4884
    }
];

    this.sortSpecs = function () {
        this.results = results.sort(function sortFunction(a, b) {
    if (a.sessionId < b.sessionId) return -1;else if (a.sessionId > b.sessionId) return 1;

    if (a.timestamp < b.timestamp) return -1;else if (a.timestamp > b.timestamp) return 1;

    return 0;
});

    };

    this.setTitle = function () {
        var title = $('.report-title').text();
        titleService.setTitle(title);
    };

    // is run after all test data has been prepared/loaded
    this.afterLoadingJobs = function () {
        this.sortSpecs();
        this.setTitle();
    };

    this.loadResultsViaAjax = function () {

        $http({
            url: './combined.json',
            method: 'GET'
        }).then(function (response) {
                var data = null;
                if (response && response.data) {
                    if (typeof response.data === 'object') {
                        data = response.data;
                    } else if (response.data[0] === '"') { //detect super escaped file (from circular json)
                        data = CircularJSON.parse(response.data); //the file is escaped in a weird way (with circular json)
                    } else {
                        data = JSON.parse(response.data);
                    }
                }
                if (data) {
                    results = data;
                    that.afterLoadingJobs();
                }
            },
            function (error) {
                console.error(error);
            });
    };


    if (clientDefaults.useAjax) {
        this.loadResultsViaAjax();
    } else {
        this.afterLoadingJobs();
    }

}]);

app.filter('bySearchSettings', function () {
    return function (items, searchSettings) {
        var filtered = [];
        if (!items) {
            return filtered; // to avoid crashing in where results might be empty
        }
        var prevItem = null;

        for (var i = 0; i < items.length; i++) {
            var item = items[i];
            item.displaySpecName = false;

            var isHit = false; //is set to true if any of the search criteria matched
            countLogMessages(item); // modifies item contents

            var hasLog = searchSettings.withLog && item.browserLogs && item.browserLogs.length > 0;
            if (searchSettings.description === '' ||
                (item.description && item.description.toLowerCase().indexOf(searchSettings.description.toLowerCase()) > -1)) {

                if (searchSettings.passed && item.passed || hasLog) {
                    isHit = true;
                } else if (searchSettings.failed && !item.passed && !item.pending || hasLog) {
                    isHit = true;
                } else if (searchSettings.pending && item.pending || hasLog) {
                    isHit = true;
                }
            }
            if (isHit) {
                checkIfShouldDisplaySpecName(prevItem, item);

                filtered.push(item);
                prevItem = item;
            }
        }

        return filtered;
    };
});

//formats millseconds to h m s
app.filter('timeFormat', function () {
    return function (tr, fmt) {
        if(tr == null){
            return "NaN";
        }

        switch (fmt) {
            case 'h':
                var h = tr / 1000 / 60 / 60;
                return "".concat(h.toFixed(2)).concat("h");
            case 'm':
                var m = tr / 1000 / 60;
                return "".concat(m.toFixed(2)).concat("min");
            case 's' :
                var s = tr / 1000;
                return "".concat(s.toFixed(2)).concat("s");
            case 'hm':
            case 'h:m':
                var hmMt = tr / 1000 / 60;
                var hmHr = Math.trunc(hmMt / 60);
                var hmMr = hmMt - (hmHr * 60);
                if (fmt === 'h:m') {
                    return "".concat(hmHr).concat(":").concat(hmMr < 10 ? "0" : "").concat(Math.round(hmMr));
                }
                return "".concat(hmHr).concat("h ").concat(hmMr.toFixed(2)).concat("min");
            case 'hms':
            case 'h:m:s':
                var hmsS = tr / 1000;
                var hmsHr = Math.trunc(hmsS / 60 / 60);
                var hmsM = hmsS / 60;
                var hmsMr = Math.trunc(hmsM - hmsHr * 60);
                var hmsSo = hmsS - (hmsHr * 60 * 60) - (hmsMr*60);
                if (fmt === 'h:m:s') {
                    return "".concat(hmsHr).concat(":").concat(hmsMr < 10 ? "0" : "").concat(hmsMr).concat(":").concat(hmsSo < 10 ? "0" : "").concat(Math.round(hmsSo));
                }
                return "".concat(hmsHr).concat("h ").concat(hmsMr).concat("min ").concat(hmsSo.toFixed(2)).concat("s");
            case 'ms':
                var msS = tr / 1000;
                var msMr = Math.trunc(msS / 60);
                var msMs = msS - (msMr * 60);
                return "".concat(msMr).concat("min ").concat(msMs.toFixed(2)).concat("s");
        }

        return tr;
    };
});


function PbrStackModalController($scope, $rootScope) {
    var ctrl = this;
    ctrl.rootScope = $rootScope;
    ctrl.getParent = getParent;
    ctrl.getShortDescription = getShortDescription;
    ctrl.convertTimestamp = convertTimestamp;
    ctrl.isValueAnArray = isValueAnArray;
    ctrl.toggleSmartStackTraceHighlight = function () {
        var inv = !ctrl.rootScope.showSmartStackTraceHighlight;
        ctrl.rootScope.showSmartStackTraceHighlight = inv;
    };
    ctrl.applySmartHighlight = function (line) {
        if ($rootScope.showSmartStackTraceHighlight) {
            if (line.indexOf('node_modules') > -1) {
                return 'greyout';
            }
            if (line.indexOf('  at ') === -1) {
                return '';
            }

            return 'highlight';
        }
        return '';
    };
}


app.component('pbrStackModal', {
    templateUrl: "pbr-stack-modal.html",
    bindings: {
        index: '=',
        data: '='
    },
    controller: PbrStackModalController
});

function PbrScreenshotModalController($scope, $rootScope) {
    var ctrl = this;
    ctrl.rootScope = $rootScope;
    ctrl.getParent = getParent;
    ctrl.getShortDescription = getShortDescription;

    /**
     * Updates which modal is selected.
     */
    this.updateSelectedModal = function (event, index) {
        var key = event.key; //try to use non-deprecated key first https://developer.mozilla.org/de/docs/Web/API/KeyboardEvent/keyCode
        if (key == null) {
            var keyMap = {
                37: 'ArrowLeft',
                39: 'ArrowRight'
            };
            key = keyMap[event.keyCode]; //fallback to keycode
        }
        if (key === "ArrowLeft" && this.hasPrevious) {
            this.showHideModal(index, this.previous);
        } else if (key === "ArrowRight" && this.hasNext) {
            this.showHideModal(index, this.next);
        }
    };

    /**
     * Hides the modal with the #oldIndex and shows the modal with the #newIndex.
     */
    this.showHideModal = function (oldIndex, newIndex) {
        const modalName = '#imageModal';
        $(modalName + oldIndex).modal("hide");
        $(modalName + newIndex).modal("show");
    };

}

app.component('pbrScreenshotModal', {
    templateUrl: "pbr-screenshot-modal.html",
    bindings: {
        index: '=',
        data: '=',
        next: '=',
        previous: '=',
        hasNext: '=',
        hasPrevious: '='
    },
    controller: PbrScreenshotModalController
});

app.factory('TitleService', ['$document', function ($document) {
    return {
        setTitle: function (title) {
            $document[0].title = title;
        }
    };
}]);


app.run(
    function ($rootScope, $templateCache) {
        //make sure this option is on by default
        $rootScope.showSmartStackTraceHighlight = true;
        
  $templateCache.put('pbr-screenshot-modal.html',
    '<div class="modal" id="imageModal{{$ctrl.index}}" tabindex="-1" role="dialog"\n' +
    '     aria-labelledby="imageModalLabel{{$ctrl.index}}" ng-keydown="$ctrl.updateSelectedModal($event,$ctrl.index)">\n' +
    '    <div class="modal-dialog modal-lg m-screenhot-modal" role="document">\n' +
    '        <div class="modal-content">\n' +
    '            <div class="modal-header">\n' +
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
    '                    <span aria-hidden="true">&times;</span>\n' +
    '                </button>\n' +
    '                <h6 class="modal-title" id="imageModalLabelP{{$ctrl.index}}">\n' +
    '                    {{$ctrl.getParent($ctrl.data.description)}}</h6>\n' +
    '                <h5 class="modal-title" id="imageModalLabel{{$ctrl.index}}">\n' +
    '                    {{$ctrl.getShortDescription($ctrl.data.description)}}</h5>\n' +
    '            </div>\n' +
    '            <div class="modal-body">\n' +
    '                <img class="screenshotImage" ng-src="{{$ctrl.data.screenShotFile}}">\n' +
    '            </div>\n' +
    '            <div class="modal-footer">\n' +
    '                <div class="pull-left">\n' +
    '                    <button ng-disabled="!$ctrl.hasPrevious" class="btn btn-default btn-previous" data-dismiss="modal"\n' +
    '                            data-toggle="modal" data-target="#imageModal{{$ctrl.previous}}">\n' +
    '                        Prev\n' +
    '                    </button>\n' +
    '                    <button ng-disabled="!$ctrl.hasNext" class="btn btn-default btn-next"\n' +
    '                            data-dismiss="modal" data-toggle="modal"\n' +
    '                            data-target="#imageModal{{$ctrl.next}}">\n' +
    '                        Next\n' +
    '                    </button>\n' +
    '                </div>\n' +
    '                <a class="btn btn-primary" href="{{$ctrl.data.screenShotFile}}" target="_blank">\n' +
    '                    Open Image in New Tab\n' +
    '                    <span class="glyphicon glyphicon-new-window" aria-hidden="true"></span>\n' +
    '                </a>\n' +
    '                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</div>\n' +
     ''
  );

  $templateCache.put('pbr-stack-modal.html',
    '<div class="modal" id="modal{{$ctrl.index}}" tabindex="-1" role="dialog"\n' +
    '     aria-labelledby="stackModalLabel{{$ctrl.index}}">\n' +
    '    <div class="modal-dialog modal-lg m-stack-modal" role="document">\n' +
    '        <div class="modal-content">\n' +
    '            <div class="modal-header">\n' +
    '                <button type="button" class="close" data-dismiss="modal" aria-label="Close">\n' +
    '                    <span aria-hidden="true">&times;</span>\n' +
    '                </button>\n' +
    '                <h6 class="modal-title" id="stackModalLabelP{{$ctrl.index}}">\n' +
    '                    {{$ctrl.getParent($ctrl.data.description)}}</h6>\n' +
    '                <h5 class="modal-title" id="stackModalLabel{{$ctrl.index}}">\n' +
    '                    {{$ctrl.getShortDescription($ctrl.data.description)}}</h5>\n' +
    '            </div>\n' +
    '            <div class="modal-body">\n' +
    '                <div ng-if="$ctrl.data.trace.length > 0">\n' +
    '                    <div ng-if="$ctrl.isValueAnArray($ctrl.data.trace)">\n' +
    '                        <pre class="logContainer" ng-repeat="trace in $ctrl.data.trace track by $index"><div ng-class="$ctrl.applySmartHighlight(line)" ng-repeat="line in trace.split(\'\\n\') track by $index">{{line}}</div></pre>\n' +
    '                    </div>\n' +
    '                    <div ng-if="!$ctrl.isValueAnArray($ctrl.data.trace)">\n' +
    '                        <pre class="logContainer"><div ng-class="$ctrl.applySmartHighlight(line)" ng-repeat="line in $ctrl.data.trace.split(\'\\n\') track by $index">{{line}}</div></pre>\n' +
    '                    </div>\n' +
    '                </div>\n' +
    '                <div ng-if="$ctrl.data.browserLogs.length > 0">\n' +
    '                    <h5 class="modal-title">\n' +
    '                        Browser logs:\n' +
    '                    </h5>\n' +
    '                    <pre class="logContainer"><div class="browserLogItem"\n' +
    '                                                   ng-repeat="logError in $ctrl.data.browserLogs track by $index"><div><span class="label browserLogLabel label-default"\n' +
    '                                                                                                                             ng-class="{\'label-danger\': logError.level===\'SEVERE\', \'label-warning\': logError.level===\'WARNING\'}">{{logError.level}}</span><span class="label label-default">{{$ctrl.convertTimestamp(logError.timestamp)}}</span><div ng-repeat="messageLine in logError.message.split(\'\\\\n\') track by $index">{{ messageLine }}</div></div></div></pre>\n' +
    '                </div>\n' +
    '            </div>\n' +
    '            <div class="modal-footer">\n' +
    '                <button class="btn btn-default"\n' +
    '                        ng-class="{active: $ctrl.rootScope.showSmartStackTraceHighlight}"\n' +
    '                        ng-click="$ctrl.toggleSmartStackTraceHighlight()">\n' +
    '                    <span class="glyphicon glyphicon-education black"></span> Smart Stack Trace\n' +
    '                </button>\n' +
    '                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>\n' +
    '            </div>\n' +
    '        </div>\n' +
    '    </div>\n' +
    '</div>\n' +
     ''
  );

    });
