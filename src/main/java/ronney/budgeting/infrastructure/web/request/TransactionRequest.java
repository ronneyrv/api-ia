package ronney.budgeting.infrastructure.web.request;

import ronney.budgeting.application.input.PersistTransactionInput;
import ronney.budgeting.domain.Category;

import io.swagger.v3.oas.annotations.media.Schema;

public record TransactionRequest(@Schema(example = "Jantar no shopping") String description, @Schema(example = "FOOD") Category category, @Schema(example = "79") long amount) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}
