package duke.logic.command.product;

import duke.logic.command.CommandResult;
import duke.logic.command.exceptions.CommandException;
import duke.logic.message.ProductMessageUtils;
import duke.logic.parser.commons.CliSyntax;
import duke.logic.parser.commons.Prefix;
import duke.logic.parser.exceptions.ParseException;
import duke.model.Model;
import duke.model.product.Product;

import static java.util.Objects.requireNonNull;

public class AddProductCommand extends ProductCommand {

    public static final String COMMAND_WORD = "add";
    private final Product toAdd;

    public static final String AUTO_COMPLETE_INDICATOR = ProductCommand.COMMAND_WORD + " " + COMMAND_WORD;
    public static final Prefix[] AUTO_COMPLETE_PARAMETERS = {
        CliSyntax.PREFIX_PRODUCT_NAME,
        CliSyntax.PREFIX_PRODUCT_INGREDIENT,
        CliSyntax.PREFIX_PRODUCT_INGREDIENT_COST,
        CliSyntax.PREFIX_PRODUCT_RETAIL_PRICE
    };

    /**
     * Constructs a AddProductCommand with the given ProductDescriptor
     */
    public AddProductCommand(ProductDescriptor descriptor) throws ParseException {
        requireNonNull(descriptor);
        this.toAdd = ProductCommandUtil.getProductFromDescriptor(descriptor);
    }


    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProduct(toAdd)) {
            throw new CommandException(String.format(ProductMessageUtils.MESSAGE_DUPLICATE_PRODUCT,
                toAdd.getProductName()));
        }

        ProductCommandUtil.verifyNewIngredients(model, toAdd);
        model.addProduct(toAdd);

        return new CommandResult(String.format(ProductMessageUtils.MESSAGE_ADD_PRODUCT_SUCCESS, toAdd.getProductName()),
                CommandResult.DisplayedPage.PRODUCT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddProductCommand)
                && toAdd.equals(((AddProductCommand) other).toAdd);
    }
}
