package chess.controller;

import chess.domain.exception.DataException;
import chess.service.ChessService;
import chess.view.ModelView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rooms")
public class ChessController {

    private static final Logger logger = LoggerFactory.getLogger(ChessController.class);
    private static final Logger fileLogger = LoggerFactory.getLogger("file");

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("")
    public ModelAndView play() throws DataException {
        final ModelAndView modelAndView = new ModelAndView("lobby");
        modelAndView.addAllObjects(ModelView.roomResponse(chessService.loadActiveRooms()));
        logger.info(">>>>> Run Program - console Logger");
        fileLogger.info(">>>> Run Program - file Logger");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView play(@PathVariable String id) throws DataException {
        final ModelAndView modelAndView = new ModelAndView("chessGame");
        modelAndView.addAllObjects(ModelView.gameResponse(
                chessService.gameInfo(id),
                id
        ));

        logger.info(">>>>> Play Game - console Logger");
        fileLogger.info(">>>> Play Game - file Logger");
        return modelAndView;
    }
}
