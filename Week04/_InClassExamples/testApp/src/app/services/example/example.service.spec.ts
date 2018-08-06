import { HttpClientModule } from '@angular/common/http';
import { TestBed, inject } from '@angular/core/testing';

import { ExampleService } from './example.service';

describe('ExampleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientModule],
      providers: [ExampleService]
    });
  });

  it('should be created', inject([ExampleService], (service: ExampleService) => {
    expect(service).toBeTruthy();
  }));
});
