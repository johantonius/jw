package com.jurnaliswarga.project.model;




import javax.persistence.*;
import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private long trx_id;
    private String amount;

    public long getTrx_id() {
        return trx_id;
    }

    public void setTrx_id(long trx_id) {
        this.trx_id = trx_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="video_transaction", joinColumns= @JoinColumn(name="trx_id"), inverseJoinColumns = @JoinColumn(name="video_id"))
    private Set<Videos> videos;

    public Set<Videos> getVideos() {
        return videos;
    }

    public void setVideos(Set<Videos> videos) {
        this.videos = videos;
    }
}
