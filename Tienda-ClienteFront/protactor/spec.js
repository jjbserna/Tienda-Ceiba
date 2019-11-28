// spec.js

//Patron page object
describe('Prueba flujo principal', function() {
    it('should have a title', function() {
      browser.get('http://localhost:4200/');
  
      expect(browser.getTitle()).toEqual('Urgencias');
    });
  });

  describe('Boton nueva urgencia', function() {
    it('crear urgencia', function() {
      browser.get('http://localhost:4200/');
  
      element(by.id('crearUrgencia')).click();
  
      expect(browser.getCurrentUrl()).toBe('http://localhost:4200/urgencia-form'); 
    });
  });

  describe('Creacion urgencia', function() {
    it('creacion nueva urgencia', function() {
      browser.get('http://localhost:4200/urgencia-form');
      element(by.id('identificacion')).sendKeys(1094942293);
      element(by.id('nombrePaciente')).sendKeys("Jhon");
      element(by.id('fechaIngreso')).sendKeys("02/02/2012");
      element(by.id("eps")).sendKeys("SURA");
      element(by.id('botonCrearUrgencia')).click();
      element(by.buttonText('OK')).click();
      
  
      expect(browser.getCurrentUrl()).toBe('http://localhost:4200/urgencias'); 
    });
  })

  describe('Boton nueva urgencia', function() {
    it('crear urgencia', function() {
      browser.get('http://localhost:4200/');
  
      element(by.id('crearUrgencia')).click();
  
      expect(browser.getCurrentUrl()).toBe('http://localhost:4200/urgencia-form'); 
    });
  });


  describe('Boton facturar urgencia', function() {
    it('facturar urgencia', function() {
      browser.get('http://localhost:4200/urgencias');
  
      element(by.id('botonFacturar')).click();
      element(by.buttonText('SI')).click();


      expect(browser.getCurrentUrl()).toBe('http://localhost:4200/factura/1094942293'); 
    });
  });

  describe('Facturar', function() {
    it('Facturar', function() {
      browser.get('http://localhost:4200/factura/1094942293');
      element(by.id('facturar')).click();
      element(by.buttonText('SI')).click();
      
  
      expect(browser.getCurrentUrl()).toBe('http://localhost:4200/urgencias'); 
    });
  })

  ;