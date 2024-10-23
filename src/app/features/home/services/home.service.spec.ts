import {getTestBed, TestBed} from '@angular/core/testing';

import { HomeService } from './home.service';

describe('HomeService', () => {
  let service: HomeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = getTestBed().inject(HomeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
