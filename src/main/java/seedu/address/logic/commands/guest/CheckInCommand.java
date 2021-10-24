package seedu.address.logic.commands.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;

/**
 * Checks in a guest into the hotel. 
 */
public class CheckInCommand extends Command {

    public static final String COMMAND_WORD = "checkin";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Checks in a guest to the hotel. "
            + "Guest Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_PASSPORT_NUMBER + "PASSPORT NUMBER "
            + PREFIX_ROOM_NUMBER + "ROOM NUMBER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ROOM_NUMBER + "123"
            + PREFIX_TAG + "Vaccinated "
            + PREFIX_TAG + "Applied Promocode \n";

    public static final String MESSAGE_SUCCESS = "New guest checked in: %1$s";
    public static final String MESSAGE_DUPLICATE_GUEST = "This guest is already checked in.";

    private final Guest toCheckIn;

    /**
     * Creates an CheckInCommand to add the specified {@code Guest}
     */
    public CheckInCommand(Guest guest) {
        requireNonNull(guest);
        toCheckIn = guest;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGuest(toCheckIn)) {
            throw new CommandException(MESSAGE_DUPLICATE_GUEST);
        }

        model.addGuest(toCheckIn);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCheckIn));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CheckInCommand // instanceof handles nulls
                && toCheckIn.equals(((CheckInCommand) other).toCheckIn));
    }
}