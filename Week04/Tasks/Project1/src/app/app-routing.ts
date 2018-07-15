import { Routes } from '@angular/router';

import { LoginComponent } from './components/login/login.component';
export const approutes: Routes = [
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent }
]

