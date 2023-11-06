package name.ulbricht.threed.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;

public final class ImageListCellFactory implements Callback<ListView<Image>, ListCell<Image>> {

    private DoubleProperty cellHeight;

    public DoubleProperty cellHeightProperty() {
        if (this.cellHeight == null) {
            this.cellHeight = new DoublePropertyBase() {

                @Override
                public Object getBean() {
                    return ImageListCellFactory.this;
                }

                @Override
                public String getName() {
                    return "cellHeight";
                }
            };
        }
        return this.cellHeight;
    }

    public double getCellHeight() {
        return this.cellHeight != null ? this.cellHeight.get() : 0;
    }

    public void setCellHeight(final double value) {
        cellHeightProperty().set(value);
    }

    @Override
    public ListCell<Image> call(final ListView<Image> param) {
        final var cell = new ImageListCell();

        final var cellHeightValue = getCellHeight();
        if (cellHeightValue > 0)
            cell.setCellHeight(cellHeightValue);

        return cell;
    }
}
