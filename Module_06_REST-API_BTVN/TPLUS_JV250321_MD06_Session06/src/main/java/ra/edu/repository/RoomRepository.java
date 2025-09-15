package ra.edu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ra.edu.model.entity.Room;
import ra.edu.model.entity.RoomStatus;
import ra.edu.model.entity.RoomType;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByRoomNumber(String roomNumber);

    Optional<Room> findByRoomNumber(String roomNumber);

    @Query("""
            from Room r
            where (:type is null or r.type = :type )
            and (:status is null or r.status = :status)
            """)
    List<Room> findAllByTypeAndStatus(
            @Param("type") RoomType type,
            @Param("status") RoomStatus status
    );
}
