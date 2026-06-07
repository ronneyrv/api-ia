package ronney.budgeting.application;

import org.springframework.stereotype.Service;
import ronney.budgeting.application.output.TransactionOutput;
import ronney.budgeting.domain.Category;
import ronney.budgeting.domain.TransactionRepository;

import java.util.List;

@Service
public class ListTransactionsByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListTransactionsByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionOutput> execute(Category category) {
        return transactionRepository.findAllByCategory(category).stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
