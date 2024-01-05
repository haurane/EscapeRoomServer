package haurane.escape.server.services;

import haurane.escape.server.dto.RoomDTO;
import haurane.escape.server.models.Room;
import haurane.escape.server.models.StaticObject;
import haurane.escape.server.repositories.RoomRepository;
import haurane.escape.server.services.impl.RoomServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void RoomService_GetRoomByUUID_ReturnRoomDTO() {
        Room room = Room.builder()
                .name("Test Room")
                .description("Test Description")
                .containedObjects(Collections.singleton(StaticObject.builder().name("Test Object").build()))
                .build();

        when(roomRepository.findByuuid(Mockito.anyString())).thenReturn(room);

        RoomDTO dto = roomService.getByUUID("1");
        Assertions.assertEquals(room.getUuid(), dto.getUuid());
        Assertions.assertEquals(room.getName(), dto.getName());
        Assertions.assertEquals(room.getDescription(), dto.getDescription());
        Assertions.assertEquals(room.getContainedObjects().stream().toList().get(0).getName(), dto.getContainedObjects().get(0).getName());
        Assertions.assertEquals(room.getContainedObjects().stream().toList().get(0).getUuid(), dto.getContainedObjects().get(0).getUuid());
    }
}
