package com.jurnaliswarga.project.controller;

import com.jurnaliswarga.project.model.Transaction;
import com.jurnaliswarga.project.model.Videos;
import com.jurnaliswarga.project.repository.TransactionRepository;
import com.jurnaliswarga.project.repository.UserRepository;
import com.jurnaliswarga.project.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value="/users/transaction")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideosRepository videosRepository;

//    @GetMapping
//    public Iterable <Transaction> findAll(){
//        return transactionRepository.findAll();
//    }
//
//    @GetMapping
//    public Iterable <Transaction> findStatus(){
//        return transactionRepository.findByStatus(true);
//    }

    @PutMapping("/update/{trx_id}")
    public Transaction updateStatus(@PathVariable(value = "trx_id") Long trx_id,
                                   @Valid @RequestBody Transaction transaction){

        Transaction trx = transactionRepository.findById(trx_id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction"+ "trx_id"+ trx_id));

        trx.setStatus(true);

        Transaction updatedTransaction = transactionRepository.save(trx);
        return updatedTransaction;
    }



    @PostMapping("/{user_id}/setTransaction")
    public Transaction createTransaction(@PathVariable (value = "user_id") Long user_id,
                                         @RequestParam(value="Videos") Long video,
                                         @Valid @RequestBody Transaction transaction){


        return userRepository.findById(user_id).map(transaksi -> {

            transaction.setUser(transaksi);

            //data video
            Optional<Videos> vid = videosRepository.findById(video);
            if (!vid.isPresent()) {
                throw new ResourceNotFoundException("Video with id " + video + " does not exist");
            }
            Videos vidio = vid.get();
            transaction.setVideos(vidio);

            //untuk amount
            int unik = (int) transaction.getTrx_id();
            int amount = vidio.getPrice()+unik;
            transaction.setAmount(vidio.getPrice()+unik);

            //set status jadi false dulu
            transaction.setStatus(false);

            return transactionRepository.save(transaction);
        }).orElseThrow(() -> new ResourceNotFoundException("user_id " + user_id + " not found"));
    }

    @DeleteMapping("/delete/{trx_id}")
    public void delete(@PathVariable long trx_id) {
        transactionRepository.deleteById(trx_id);
    }
}

