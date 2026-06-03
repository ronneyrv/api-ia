package ronney.budgeting.application;

import ronney.budgeting.application.input.PersistTransactionInput;
import ronney.budgeting.application.output.TransactionOutput;
import ronney.budgeting.domain.Transaction;
import ronney.budgeting.domain.TransactionRepository;

public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionOutput execute(PersistTransactionInput input) {
        var transaction = transactionRepository.save(new Transaction(input.description(), input.amount(), input.category()));

        return TransactionOutput.from(transaction);
    }
}
