package app;

import com.formdev.flatlaf.FlatLightLaf;
import data_access.*;
import use_case.clear_history.ClearHistoryController;
import use_case.fill_detail.FillDetailController;
import use_case.load_from_DAO.LoadFromDAOController;
import use_case.open_website.OpenWebsiteController;
import use_case.open_frame.OpenFrameController;
import use_case.query.query_all.QueryAllController;
import use_case.query.query_one.QueryOneController;
import use_case.reuse_history_query.ReuseHistoryQueryController;
import use_case.star.StarController;
import use_case.switch_results_panel.SwitchResultsPanelController;
import use_case.switch_view.SwitchViewController;
import use_case.toggle_display_option.ToggleDisplayOptionController;
import view_model.*;
import use_case.open_website.WebDataAccessInterface;
import use_case.query.QueryDataAccessInterface;
import use_case.HistoryDataAccessInterface;
import use_case.star.StarDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        configureUI();  // FlatLaf UI Look & Feel setup before any Swing codes

        //View Models
        SearchViewModel searchViewModel = new SearchViewModel();
        StarredViewModel starredViewModel = new StarredViewModel();
        HistoryViewModel historyViewModel = new HistoryViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        FrameManagerModel frameManagerModel = new FrameManagerModel();
        MainFrameViewModel mainFrameViewModel = new MainFrameViewModel();
        FrontPageViewModel frontPageViewModel = new FrontPageViewModel();

        ScrollResultsPanelModel[] resultsPanelModels = new ScrollResultsPanelModel[Database.length];
        for (int i = 0; i < Database.length; i++) {
            resultsPanelModels[i] = new ScrollResultsPanelModel(Database.get(i));
        }

        //DAOs
        WebDataAccessInterface webDAO = new WebDataAccessObject();
        CacheDataAccessInterface dataCacheDAO = new CacheDataAccessObject();
        //TODO: InMemory QueryDAO, StarDAO, HistoryDAO
        QueryDataAccessInterface queryDAO = null;

        StarDataAccessInterface starDAO = null;

        HistoryDataAccessInterface historyDAO = null;

        //Controllers
        ControllerFactory factory = new ControllerFactory(searchViewModel, frontPageViewModel, starredViewModel, historyViewModel,
                mainFrameViewModel, viewManagerModel, frameManagerModel, resultsPanelModels, webDAO, queryDAO, starDAO, historyDAO);

        StarController starController =         factory.createStarController();
        QueryAllController queryAllController = factory.createQueryAllController();
        QueryOneController queryOneController = factory.createQueryOneController();
        OpenFrameController openFrameController =   factory.createOpenFrameController();
        SwitchViewController switchViewController = factory.createSwitchViewController();
        FillDetailController fillDetailController = factory.createFillDetailController();
        LoadFromDAOController loadFromDAOController =   factory.createLoadFromDAOController();
        OpenWebsiteController openWebsiteController =   factory.createOpenWebsiteController();
        ClearHistoryController clearHistoryController = factory.createClearHistoryController();
        ReuseHistoryQueryController reuseHistoryQueryController =       factory.createReuseHistoryQueryController();
        SwitchResultsPanelController switchResultsPanelController =     factory.createSwitchResultsPanelController();
        ToggleDisplayOptionController toggleDisplayOptionController =   factory.createToggleDisplayOptionController();


        MainFrame mainFrame = new MainFrame(mainFrameViewModel);
        //TODO: MenuBar

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        mainFrame.add(views);

        //TODO: Views
        FrontPageView frontPageView = new FrontPageView(frontPageViewModel, switchViewController, openWebsiteController);
        views.add(frontPageView, FrontPageViewModel.VIEW_NAME);

        SearchView searchView = new SearchView(searchViewModel, switchViewController,
                switchResultsPanelController, queryAllController, queryOneController, toggleDisplayOptionController);
        views.add(searchView, SearchViewModel.VIEW_NAME);

        new ViewManager(views, cardLayout, viewManagerModel); // Finished adding views


        //TODO: Frame Manager, StarredView, HistoryView

        // there should be a loadFromDAOController.execute();
        // to load data from the 3 DAOs into history view, star view, and resultsPanels


        mainFrame.init();
        switchViewController.execute(FrontPageViewModel.VIEW_NAME);
    }

    private static void configureUI() {

    }

}
