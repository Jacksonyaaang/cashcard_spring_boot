package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    @GetMapping("/{cardid}")
    public ResponseEntity<CashCard> findId(@PathVariable long cardid){
        if (cardid == 99L){
            CashCard cashcard = new CashCard(99L, 120.99);
            return ResponseEntity.ok(cashcard);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}