// spec.js


describe('Prueba flujo principal', function() {
    it('debe tener titulo', function() {
        browser.get('http://localhost:4200/');

        expect(browser.getTitle()).toEqual('Tienda');
    });
});

describe('Boton crear producto', function() {
    it('crear Producto', function() {
        browser.get('http://localhost:4200/');

        element(by.id('crearProducto')).click();

        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/prenda-form');
    });
});

describe('Crear producto', function() {
    it('Creacion nuevo producto', function() {
        browser.get('http://localhost:4200/prenda-form');
        element(by.id('codigoPrenda')).sendKeys(999999);
        browser.sleep(1000);
        element(by.id('descripcion')).sendKeys("Camisa Sonar");
        browser.sleep(1000);
        element(by.id('estilo')).sendKeys("Manga Larga");
        browser.sleep(1000);
        element(by.id("precio")).sendKeys(20);
        browser.sleep(1000);
        element(by.id("stock")).sendKeys(1);
        browser.sleep(1000);
        element(by.id('estado')).click();
        browser.sleep(1000);
        element(by.id('botonCrearPrenda')).click();
        browser.sleep(1000);
        element(by.buttonText('OK')).click();
        browser.sleep(1000);
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/prenda-form');
    });
})

describe('Crear producto', function() {
    it('Creacion nuevo producto', function() {
        browser.get('http://localhost:4200/prenda-form');
        element(by.id('codigoPrenda')).sendKeys(989898);
        browser.sleep(1000);
        element(by.id('descripcion')).sendKeys("Camisa Jenkins");
        browser.sleep(1000);
        element(by.id('estilo')).sendKeys("Polo");
        browser.sleep(1000);
        element(by.id("precio")).sendKeys(30);
        browser.sleep(1000);
        element(by.id("stock")).sendKeys(1);
        browser.sleep(1000);
        element(by.id('estado')).click();
        browser.sleep(1000);
        element(by.id('botonCrearPrenda')).click();
        browser.sleep(1000);
        element(by.buttonText('OK')).click();
        browser.sleep(1000);
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/prenda-form');
    });
})


describe('Boton crear cliente', function() {
    it('crear cliente', function() {
        browser.get('http://localhost:4200/');

        element(by.id('crearCliente')).click();

        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/cliente-form');
    });
});

describe('Crear producto', function() {
    it('Creacion nuevo producto', function() {
        browser.get('http://localhost:4200/cliente-form');
        element(by.id('identificacion')).sendKeys(1094900333);
        browser.sleep(1000);
        element(by.id('nombre')).sendKeys("Jeison Julian");
        browser.sleep(1000);
        element(by.id('apellido')).sendKeys("Barbosa Serna");
        browser.sleep(1000);
        element(by.id("fechaNacimiento")).sendKeys("31/10/1988");
        browser.sleep(1000);
        element(by.id("correo")).sendKeys("jjb@ceiba.com.co");
        browser.sleep(1000);
        element(by.id('celular')).sendKeys(3117630511);
        browser.sleep(1000);
        element(by.id('ciudad')).sendKeys("Medellín");
        browser.sleep(1000);
        element(by.id('direccionEntrega')).sendKeys("B/ Rodeo Alto");
        browser.sleep(1000);
        element(by.id("usuario")).sendKeys("jjbs");
        browser.sleep(1000);
        element(by.id("password")).sendKeys("123456");
        browser.sleep(1000);
        element(by.id('botonCrearCliente')).click();
        browser.sleep(1000);
        element(by.buttonText('OK')).click();
        browser.sleep(1000);
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/cliente-form');
    });
})



describe('Boton crear pedido', function() {
    it('crear pedido', function() {
        browser.get('http://localhost:4200/tienda');

        element(by.id('crearPedido')).click();

        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/pedido-form');
    });
});

describe('Crear pedido', function() {
    it('Creacion nuevo pedido', function() {
        browser.get('http://localhost:4200/pedido-form');
        element(by.id('numeroOrden')).sendKeys(99);
        element(by.id('identificacionCliente')).sendKeys(1094900333);
        element(by.id('botonCrearPedido')).click();
        element(by.id('codigoPrenda')).sendKeys(999999);
        element(by.id('CantidadPrenda')).sendKeys(2);
        browser.sleep(1000);
        element(by.id('botonCrearOrden')).click();
        element(by.buttonText('OK')).click();
        element(by.id('codigoPrenda')).sendKeys(989898);
        element(by.id('CantidadPrenda')).sendKeys(1);
        browser.sleep(1000);
        element(by.id('botonCrearOrden')).click();
        element(by.buttonText('OK')).click();
        browser.sleep(1000);
        element(by.id('botonNuevoPedido')).click();
        browser.sleep(1000);
        element(by.buttonText('OK')).click();
        browser.sleep(1000);
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/pedido-form');
    });
});

describe('Boton generar factura', function() {
    it('generar factura', function() {
        browser.get('http://localhost:4200/tienda');

        element(by.id('generarFactura')).click();

        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/factura-form');
    });
});


describe('Generar factura', function() {
    it('Generacion de factura', function() {
        browser.get('http://localhost:4200/factura-form');
        element(by.id('numeroOrden')).sendKeys(99);
        element(by.id('facturar')).click();
        element(by.buttonText('volver')).click();
        browser.sleep(1000);
        element(by.buttonText('SI')).click();
        expect(browser.getCurrentUrl()).toBe('http://localhost:4200/tienda');
    });
});