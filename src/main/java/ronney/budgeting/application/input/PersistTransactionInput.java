package ronney.budgeting.application.input;

import ronney.budgeting.domain.Category;

public record PersistTransactionInput(String description, long amount, Category category) {
}
