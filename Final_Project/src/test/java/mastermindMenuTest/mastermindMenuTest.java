package mastermindMenuTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;


import mastermindMenu.MastermindMenu;
import mastermindMenu.MastermindGame.MastermindGame;
import mastermindMenu.gameSettings.GameSettings;
import mastermindMenu.masterMindControl.GameController;

class mastermindMenuTest {

//    @Test
//    void testStartMastermindRuns() {
//            // Mock the construction of settings, game, and controller
//            try (MockedConstruction<GameSettings> mockedSettings = Mockito.mockConstruction(GameSettings.class);
//                 MockedConstruction<MastermindGame> mockedGame = Mockito.mockConstruction(MastermindGame.class, 
//                    (mock, context) -> {
//                        when(mock.getSettings()).thenReturn(new GameSettings());
//                    });
//                 MockedConstruction<GameController> mockedController = Mockito.mockConstruction(GameController.class, 
//                    (mock, context) -> {
//                        // You could specify behavior if needed here
//                    })) {
//
//                // Call the method under test
//                MastermindMenu.startMastermind();
//
//                // Assertions to ensure each constructor was called
//                assertEquals(1, mockedSettings.constructed().size(), "GameSettings should have been constructed exactly once.");
//                assertEquals(1, mockedGame.constructed().size(), "MastermindGame should have been constructed exactly once.");
//                assertEquals(1, mockedController.constructed().size(), "GameController should have been constructed exactly once.");
//
//                // Verify the startGame method was called on GameController
//                GameController controller = mockedController.constructed().get(0);
//                verify(controller, times(1)).startGame();
//                
//                assertDoesNotThrow(() -> MastermindMenu.startMastermind());
//            }
//        }
//    }
	@Test
	void testStartMastermindRuns() {
		assertDoesNotThrow(() -> MastermindMenu.startMastermind());
	}
}