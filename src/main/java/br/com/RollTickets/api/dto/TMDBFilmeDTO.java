package br.com.RollTickets.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TMDBFilmeDTO {

    private String title;

    @JsonProperty("overview")
    private String sinopse;

    @JsonProperty("poster_path")
    private String imageUrl;

    @JsonProperty("genre_ids")
    private int[] generoIds;

    @JsonProperty("vote_average")
    private double nota;

    @JsonProperty("original_language")
    private String idiomaOriginal;

    @JsonProperty("popularity")
    private double popularidade;

    private int id;

    @JsonProperty("original_title")
    private String tituloOriginal;

    @JsonProperty("release_date")
    private String dataLancamento;

    public String getTitle() { return title; }
    public String getSinopse() { return sinopse; }
    public String getImageUrl() { return imageUrl; }
    public int[] getGeneroIds() { return generoIds; }
    public double getNota() { return nota; }
    public String getIdiomaOriginal() { return idiomaOriginal; }
    public double getPopularidade() { return popularidade; }
    public int getId() { return id; }
    public String getTituloOriginal() { return tituloOriginal; }
    public String getDataLancamento() { return dataLancamento; }
}
