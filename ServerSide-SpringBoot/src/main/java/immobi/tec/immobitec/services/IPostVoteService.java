package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.TypeVote;

public interface IPostVoteService {



     void vote(int idPost, TypeVote typeVote,int idUser);



}
