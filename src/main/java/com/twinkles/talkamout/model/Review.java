package com.twinkles.talkamout.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseClass {
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User givenBy;
  private String subject;
  private String body;
}
