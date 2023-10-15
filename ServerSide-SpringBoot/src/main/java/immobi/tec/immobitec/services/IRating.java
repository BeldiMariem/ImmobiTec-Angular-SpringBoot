package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Rating;

import java.util.List;

public interface IRating {

    public Rating getRatingById(int id);

    public List<Rating> getAllRatings();

    public Rating addRating(int id_user,int id,Rating r);

    public Rating updateRating(Rating r);

    public void deleteRating(int id);
}
