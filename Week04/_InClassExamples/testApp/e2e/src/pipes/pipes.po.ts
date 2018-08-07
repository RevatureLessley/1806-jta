/*
    This is your po file. What is a po file? It is a page object file!
    Here we will do what we did in Selenium as well and provide a repository for all the
    needed objects and elements we could wnat for testing.
*/

import { browser, by, element } from 'protractor';

export class PipesPage {
    navigateTo(){
        return browser.get('/pipes');
    }
    getTitleText(){
        return element(by.css('#titleCard')).getText();
    }
    inputTextbox(input){
        element(by.css('input')).sendKeys(input);
    }
    getPipeText(){
        return element(by.css('#pipeText')).getText();
    }
    getPipedTextAfterInput(input){
        this.inputTextbox(input);
        return this.getPipeText();
    }
}

  /*
    Using selenium protractor should be intuitive.
    Some tricks:
        //Browser navigation
        browser.get('yoururl');
        browser.navigate().back();
        browser.navigate().forward();

        //Waits
        browser.sleep(10000); 
        browser.waitForAngular();
        browser.getLocationAbsUrl() //get the current address
        browser.ignoreSynchronization = true;

        //Waiting for an element to be visible
        browser.wait(function() {
            return element(by.id('create')).isPresent();
        }, 5000);

        use element.all(by.something) to get the collection
            then access it thorugh a for loop or el.get(number)

        //Keyboard actions:
        element(by.id('user_name').sendKeys("user1");
        sendKeys(protractor.Key.ENTER);
        sendKeys(protractor.Key.TAB);
        element(by.id('user_name')).clear()


  */