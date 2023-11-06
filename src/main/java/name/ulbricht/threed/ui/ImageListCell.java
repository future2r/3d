package name.ulbricht.threed.ui;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class ImageListCell extends ListCell<Image> {

    private DoubleProperty cellHeight;

    public DoubleProperty cellHeightProperty() {
        if (this.cellHeight == null) {
            this.cellHeight = new DoublePropertyBase() {

                @Override
                public Object getBean() {
                    return ImageListCell.this;
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
    public void updateItem(final Image item, final boolean empty) {
        super.updateItem(item, empty);

        if (!empty && item != null) {
            final var imageView = new ImageView(item);
            imageView.setPreserveRatio(true);
            
            final var cellHeightValue = getCellHeight();
            if (cellHeightValue > 0)
                imageView.setFitHeight(cellHeightValue);
            setGraphic(imageView);
        } else {
            setGraphic(null);
        }
    }
}
