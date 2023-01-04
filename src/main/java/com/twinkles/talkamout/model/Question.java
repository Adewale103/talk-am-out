package com.twinkles.talkamout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question extends BaseClass {
    private String question;
    private int questionNumber;

    @OneToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private Answer answer;

}
