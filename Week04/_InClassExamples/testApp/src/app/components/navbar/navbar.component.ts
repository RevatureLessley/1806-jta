import { Component } from "@angular/core";

/*
    Angular components represent a section of your applicaiton. Typically
    multiple components are brought together in a module to make a full 
    application. The component helps vind a view with a controller.
*/

//Decorator
/*
    Any annotated word(s) are called decorators. 
    They serve to provide metadata for the angular application.
    For instnace, this tells angualr that its contents are to be used
    for the entire component bundle.
*/
@Component({
    //Selector
    /*
        The selector is needed in order to inject views. The selector
        indicates what the tag name should be for the injection site of this
        component.
    */
    selector: 'app-navbar',

    //Template URL
    /*
        The template URL is used to point at a specific file to be used
        as the fornt end for the specific component.
    */
    templateUrl: './navbar.component.html',

    //StyleUrls
    /*
        In addition to using the global css file, you can also bring in
        other css files to give a customized to each individual component.
        You can have 0-many css files brought in, ergo, you should use
        an array for it.
    */
    styleUrls: ['./navbar.component.css']
})

/*
    All components require a class to be identified by. This allows us
    to import the component for use in the application. This also 
    provides a section for storing/retrieving data in between the front
    and backend (Of the client side application)
    NOTE: Though I refer to it as front and backend, remember that the
    TRUE backend of your application will always reference the server
    side.
*/
export class NavbarComponent {

}