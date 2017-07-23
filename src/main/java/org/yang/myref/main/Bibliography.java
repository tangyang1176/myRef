package org.yang.myref.main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Bibliography {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "author")
	@NotEmpty(message = "Author is required.")
	private String author;
	
	@Column(name = "title")
	@NotEmpty(message = "Title field is required.")
	private String title;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "journal")
	private String journal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
	} 
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Bibliography))
			return false;
		
		Bibliography other = (Bibliography) obj;
		return author.equals(other.author) && title.equals(other.title) &&
			   year.equals(other.year)  && journal.equals(other.journal);
	}
}
