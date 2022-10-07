package com.patreonshout.beans;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="webaccounts")
public class WebAccountBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="webaccount_id")
	int webaccount_id;

	@Column(name="username")
	String username;

	@Column(name="password")
	String password;
}
