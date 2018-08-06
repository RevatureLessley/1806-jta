// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage-istanbul-reporter'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      clearContext: false // leave Jasmine Spec Runner output visible in browser
    },
    coverageIstanbulReporter: {
      dir: require('path').join(__dirname, '../coverage'),
      reports: ['html', 'lcovonly'],
      fixWebpackSourcePaths: true
    },
    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    /*
      Should you want to run your tests as headless, you would need
      to configure a cusotm browser.
      Chrome, as it were, coems with a headless browser option,
      how convenient.
      To configure such a set goes as follows:
    */
   customLauncher: {
     ChromeHeadless: {
       base: 'Chrome',
       flags: [
         '--headless--',
         '--disable-gpu',
          '--no-sandbox',
          '--remote-debugging-port-9222'
       ]
     }
   },
   //USE BELOW CONFIG FOR NORMAL TESTING WIHT BROWSERS OPENING
   // browsers: ['Chrome'],
   // singleRun: false
   //USE BELOW CONFIG FOR HEADLESS TESTING
   browsers: ['ChromeHeadless'],
   singleRun: true
  });
};