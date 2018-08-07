import { PipesPage } from './pipes.po';

describe('workspace-project Pipes', () => {
  let page: PipesPage;

  beforeEach(() => {
    page = new PipesPage();
  });

  it('Custom pipe works as intended', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('PIPES');
    expect(page.getPipedTextAfterInput("this is a test")).toEqual("thIs Is A tEst");
  });
});