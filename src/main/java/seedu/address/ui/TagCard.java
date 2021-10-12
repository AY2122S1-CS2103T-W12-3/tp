package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tag.Tag;

public class TagCard extends UiPart<Region> {

    private static final String FXML = "TagListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tag tag;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label tagName;

    /**
     * Creates a {@code TagCode} with the given {@code Tag} and index to display.
     */
    public TagCard(Tag tag, int displayedIndex) {
        super(FXML);
        this.tag = tag;
        id.setText(displayedIndex + ". ");
        tagName.setText(tag.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCard)) {
            return false;
        }

        // state check
        TagCard card = (TagCard) other;
        return id.getText().equals(card.id.getText())
                && tag.equals(card.tag);
    }
    
}
