package org.mabufudyne.designer.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.mabufudyne.designer.core.StoryPiece;

public class OverviewController extends WindowSubController {
    @FXML private TableView<StoryPiece> storyPiecesTable;
    @FXML private TableColumn<StoryPiece, Integer> orderColumn;
    @FXML private TableColumn<StoryPiece, String> titleColumn;
    @FXML private Button btnRemoveStoryPiece;

    @Override
    public void populateControls() {
        orderColumn.setCellValueFactory(cellData -> cellData.getValue().orderProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        storyPiecesTable.setItems(app.getActiveAdventure().getStoryPieces());
    }

    /** Getters and Setters **/

    public StoryPiece getSelectedStoryPiece() {
        return storyPiecesTable.getSelectionModel().getSelectedItem();
    }

    public TableView<StoryPiece> getStoryPiecesTable() {
        return storyPiecesTable;
    }

    public void setStoryPiecesTable(TableView<StoryPiece> storyPiecesTable) {
        this.storyPiecesTable = storyPiecesTable;
    }

    public Button getBtnRemoveStoryPiece() {
        return btnRemoveStoryPiece;
    }

    public void setBtnRemoveStoryPiece(Button btnRemoveStoryPiece) {
        this.btnRemoveStoryPiece = btnRemoveStoryPiece;
    }

    // TODO: Attach listeners in one place (some are defined in FXML, some are defined in Java code)
    public void onAddStoryPieceClick() {
        app.getActiveAdventure().addStoryPiece(new StoryPiece());
        btnRemoveStoryPiece.setDisable(false);
    }

    public void onRemoveStoryPieceClick() {
        app.getActiveAdventure().removeStoryPiece(storyPiecesTable.getSelectionModel().getSelectedItem());
        btnRemoveStoryPiece.setDisable(app.getActiveAdventure().getStoryPieces().size() == 1);
    }
}
