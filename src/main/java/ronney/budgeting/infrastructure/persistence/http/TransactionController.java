package ronney.budgeting.infrastructure.persistence.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ronney.budgeting.application.ListTransactionsByCategoryUseCase;
import ronney.budgeting.application.PersistTransactionUseCase;
import ronney.budgeting.domain.Category;
import ronney.budgeting.infrastructure.persistence.http.request.TransactionRequest;
import ronney.budgeting.infrastructure.persistence.http.response.TransactionResponse;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase, ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase) {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category) {
        return listTransactionsByCategoryUseCase.execute(category).stream()
                .map(TransactionResponse::from)
                .toList();
    }
}
