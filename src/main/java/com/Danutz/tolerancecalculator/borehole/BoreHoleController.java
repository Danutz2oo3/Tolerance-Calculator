package com.Danutz.tolerancecalculator.borehole;

import com.Danutz.tolerancecalculator.user.UserEntity;
import com.Danutz.tolerancecalculator.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/history")
public class BoreHoleController {
    private final BoreHoleService boreHoleService;
    private final UserService userService;

    @Autowired
    public BoreHoleController(BoreHoleService boreHoleService, UserService userService) {
        this.boreHoleService = boreHoleService;
        this.userService = userService;
    }

    @GetMapping(path = {"get"})
    public List<BoreHoleEntity> getBoreHole(
            @RequestParam(value = "id") Long id
    ){
        return boreHoleService.getBoreHole(id);
    }

    @PostMapping(path = {"add"})
    public ResponseEntity<BoreHoleEntity> addBoreHole(
            @RequestParam BigDecimal nominalDimension,
            @RequestParam BigDecimal upperLimitDeviation,
            @RequestParam BigDecimal lowerLimitDeviation,
            @RequestParam Long user_id
            ) {
        UserEntity userEntity = userService.getUserById(user_id);
        BoreHoleEntity boreHoleEntity = boreHoleService.addBoreHole(nominalDimension, upperLimitDeviation, lowerLimitDeviation, userEntity);
        return ResponseEntity.ok(boreHoleEntity);
    }
}
