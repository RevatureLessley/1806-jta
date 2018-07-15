import { InMemoryDbService } from 'angular-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes = [
        { id: 11, name: 'Mr. Doom'},
        { id: 12, name: 'Veritesa'},
        { id: 13, name: 'Arctic'},
        { id: 14, name: 'Catistrophia'},
        { id: 15, name: 'Ultimus'},
        { id: 16, name: 'Explosivo'},
        { id: 17, name: 'Reborn'},
        { id: 18, name: 'Whisp'},
        { id: 19, name: 'Zenith'},
        { id: 20, name: 'Aerth'}
    ];
    return {heroes};
  }
}