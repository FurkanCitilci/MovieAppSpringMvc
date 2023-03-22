package com.furkancitilci.utility.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sample{

public Long id;
	@Column(length = 1024)
public String url;
	@Column(length = 1024)
public String name;
	@Column(length = 1024)
String type;
	@Column(length = 1024)
public String language;
public List<String> genres;
	@Column(length = 1024)
String status;
int runtime;
int averageRuntime;
@Column(length = 1024)
public String premiered;
String ended;
String officialSite;
Schedule schedule;
public Rating rating;
int weight;
public Network network;
WebChannel webChannel;
DvdCountry dvdCountry;
Externals externals;
	@Column(length = 1024)
public Image image;
	@Lob
	public String summary;
	public  int updated;
	@Column(length = 1024)
	public  _links _links;




}