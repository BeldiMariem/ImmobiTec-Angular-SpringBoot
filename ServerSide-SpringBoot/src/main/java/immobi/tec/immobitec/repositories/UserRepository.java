package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.DTO.UserDTO;
import immobi.tec.immobitec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends PagingAndSortingRepository<AppUser,Integer> {
    AppUser findByEmail (String email);
    UserDTO findAppUserByEmail (String email);
    @Modifying
    @Transactional
    @Query("delete From AppUser u where u.id_user=:idUser")
    void DeleteUser(@Param("idUser") int idUser );

AppUser findByName( String name );
AppUser findByNameAndLastname(String name, String lastname);

    @Query("SELECT u FROM AppUser u WHERE u.verificationCode = ?1")
    public AppUser findByVerificationCode(String code);

    public AppUser findByResetPasswordToken(String token);

}
