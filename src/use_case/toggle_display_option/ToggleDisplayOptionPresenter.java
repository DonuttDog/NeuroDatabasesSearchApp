package use_case.toggle_display_option;

import view_model.ScrollResultsPanelModel;
import view_model.SearchViewModel;
import view_model.StarredViewModel;

public class ToggleDisplayOptionPresenter implements ToggleDisplayOptionOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final StarredViewModel starredViewModel;
    private final ScrollResultsPanelModel[] resultsPanelModels;

    public ToggleDisplayOptionPresenter(SearchViewModel searchViewModel, StarredViewModel starredViewModel, ScrollResultsPanelModel[] resultsPanelModels) {
        this.searchViewModel = searchViewModel;
        this.starredViewModel = starredViewModel;
        this.resultsPanelModels = resultsPanelModels;
    }

    @Override
    public void prepareSuccessView(ToggleDisplayOptionOutputData outputData) {


    }
}
