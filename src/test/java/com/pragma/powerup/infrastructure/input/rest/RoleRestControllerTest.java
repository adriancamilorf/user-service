import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.handler.impl.RoleHandler;
import com.pragma.powerup.infrastructure.input.rest.RoleRestController;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class RoleRestControllerTest {

    @InjectMocks
    private RoleRestController roleRestController;

    @Mock
    private RoleHandler roleHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        RoleResponseDto roleResponseDto = new RoleResponseDto();
        roleResponseDto.setName("OWNER");
        when(roleHandler.getRoleByUserId(userId)).thenReturn(roleResponseDto);
        ResponseEntity<RoleResponseDto> result = roleRestController.getUserById(userId);
        verify(roleHandler, times(1)).getRoleByUserId(userId);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(roleResponseDto, result.getBody());
    }
}
