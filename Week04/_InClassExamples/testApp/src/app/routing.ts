/*
    Routing is a feature that Angular uses to acheive true
    single Page Application format. (SPA)

    Routing is where we set up a tag that will take injections of a 
    separate html code/pages dynamically. This lets the user stay on the sam page,
    while new content is replaced throughout navigation.
    The pages and content get cahced client side so that the user does not need to hit
    the server for new pages or old ones.

    Worth noting: The cachin utilizes browser bookmakrs to maintain page history.
    This allows us to use the back/forward buttons on the page even though, technically,
    the page is never actually changed.
*/

import { Routes } from '@angular/router';

import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';
import { DirectivesComponent } from './components/directives/directives.component'

export const approutes: Routes = [
    {
        //The URL mapping
        path: 'interpolation',
        component: InterpolationComponent
    },
    {
        //The URL mapping
        path: 'directives',
        component: DirectivesComponent
    },
    {
        //The URL mapping
        path: 'pokeapi',
        component: PokeapiComponent
    }
]

