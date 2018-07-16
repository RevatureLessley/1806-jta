import { TestBed, inject } from '@angular/core/testing';

import { ExampleService } from './example.service';

describe('ExampleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExampleService]
    });
  });

  it('should be created', inject([ExampleService], (service: ExampleService) => {
    expect(service).toBeTruthy();
  }));
});
