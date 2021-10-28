//package seedu.address.logic.commands.guest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//import org.junit.jupiter.api.Test;
//
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.guest.Archive;
//import seedu.address.model.tag.Tag;
//import seedu.address.model.vendor.VendorBook;
//
//public class FilterGuestCommandTest {
//
//    private Model model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
//    private Model expectedModel =
//            new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
//
//    @Test
//    public void equals() {
//        GuestPredicate firstPredicate =
//                new GuestPredicate(Collections.singletonList(new Tag("123")));
//        GuestPredicate secondPredicate =
//                new GuestPredicate(Collections.singletonList(new Tag("123456121D")));
//
//        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
//        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);
//
//        // same object -> returns true
//        assertTrue(filterFirstCommand.equals(filterFirstCommand));
//
//        // same values -> returns true
//        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
//        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));
//
//        // different types -> returns false
//        assertFalse(filterFirstCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(filterFirstCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(filterFirstCommand.equals(filterSecondCommand));
//    }
//
//    @Test
//    public void execute_noTags_noPersonFiltered() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
//        GuestPredicate predicate = preparePredicate("  ");
//        FilterCommand command = new FilterCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        expectedModel.updateFilteredTagList(prepareTagPredicate(predicate));
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private GuestPredicate preparePredicate(String userInput) {
//        return new GuestPredicate(Arrays.asList(userInput.split("\\s+")).stream().map(Tag::new).collect(
//                Collectors.toList()));
//    }
//
//    private Predicate<Tag> prepareTagPredicate(GuestPredicate predicate) {
//        return tag -> predicate.getTags().contains(tag);
//    }
//
//}