package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAFF_ID;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_DEFAULT;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_DEFAULT;
import static seedu.address.testutil.TypicalStaffIds.STAFF_ID_FIRST_PERSON;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.guest.CheckInCommand;
import seedu.address.logic.commands.guest.ClearGuestCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditGuestDescriptor;
import seedu.address.logic.commands.EditCommand.EditStaffDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.Guest;
import seedu.address.model.IdentifierContainsKeywordsPredicate;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;
import seedu.address.testutil.guest.GuestBuilder;
import seedu.address.testutil.guest.GuestUtil;
import seedu.address.testutil.vendor.VendorBuilder;
import seedu.address.testutil.vendor.StaffUtil;

public class PocketHotelParserTest {

    private final PocketHotelParser parser = new PocketHotelParser();

    @Test
    public void parseCommand_addGuest() throws Exception {
        Guest guest = new GuestBuilder().build();
        CheckInCommand command = (CheckInCommand) parser.parseCommand(GuestUtil.getAddCommand(guest));
        assertEquals(new CheckInCommand(guest), command);
    }

    @Test
    public void parseCommand_addStaff() throws Exception {
        Staff staff = new VendorBuilder().build();
        CheckInCommand command = (CheckInCommand) parser.parseCommand(StaffUtil.getAddCommand(staff));
        assertEquals(new CheckInCommand(staff), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearGuestCommand.COMMAND_WORD) instanceof ClearGuestCommand);
        assertTrue(parser.parseCommand(ClearGuestCommand.COMMAND_WORD + " 3") instanceof ClearGuestCommand);
    }

    @Test
    public void parseCommand_deleteStaff() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " "
                        + PREFIX_STAFF_ID + STAFF_ID_FIRST_PERSON.toString());
        assertEquals(new DeleteCommand(STAFF_ID_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteGuest() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " "
                        + PREFIX_PASSPORT_NUMBER + PASSPORT_NUMBER_FIRST_PERSON.toString());
        assertEquals(new DeleteCommand(PASSPORT_NUMBER_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editGuest() throws Exception {
        Guest guest = new GuestBuilder().build();
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PASSPORT_NUMBER_DEFAULT + " " + GuestUtil.getEditGuestDescriptorDetails(descriptor));
        assertEquals(new EditCommand(PASSPORT_NUMBER_DEFAULT, descriptor), command);
    }

    @Test
    public void parseCommand_editStaff() throws Exception {
        Staff staff = new VendorBuilder().build();
        EditStaffDescriptor descriptor = new EditVendorDescriptorBuilder(staff).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + STAFF_ID_DEFAULT.toString() + " " + StaffUtil.getEditStaffDescriptorDetails(descriptor));
        assertEquals(new EditCommand(STAFF_ID_DEFAULT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_view() throws Exception {
        ViewCommand command = (ViewCommand) parser.parseCommand(
                ViewCommand.COMMAND_WORD + " " + PREFIX_STAFF_ID + STAFF_ID_FIRST_PERSON.toString());
        assertEquals(
                new ViewCommand(new IdentifierContainsKeywordsPredicate(List.of(STAFF_ID_FIRST_PERSON.toString()))),
                command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}