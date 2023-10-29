package example.cashcard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    private CashCardRepository cashCardRepository;
    //constructor --> to show di in the signature
    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }
    //the controller method to handle the GET request
    @GetMapping("/{cardid}")
    public ResponseEntity<CashCard> findId(@PathVariable long cardid){
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(cardid);
        if (cashCardOptional.isPresent()){
            return ResponseEntity.ok(cashCardOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    //the POST request handler (controller)
    @PostMapping
    public ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){
//        return null; //when return null, spring will return automatically the status code 200
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
        //to build the location of the new cashcard
        URI locationOfNewCashCard = ucb.path("cashcards/{id}").buildAndExpand(savedCashCard.id()).toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

}