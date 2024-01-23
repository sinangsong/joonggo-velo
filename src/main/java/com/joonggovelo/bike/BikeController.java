package com.joonggovelo.bike;

import com.joonggovelo.user.User;
import com.joonggovelo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class BikeController {

    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;

    @Autowired
    public BikeController(BikeRepository bikeRepository, UserRepository userRepository) {
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;
    }

    // 자전거 생성
    @PostMapping("users/{id}/bikes")
    public ResponseEntity<Bike> createBicycle(@PathVariable int id, @RequestBody Bike bike) {
        // 사용자 ID 확인 및 연결
        //int ownerId = bicycle.getOwner().getId();
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            bike.setUser(user.get());
            Bike savedBicycle = bikeRepository.save(bike);
            return new ResponseEntity<>(savedBicycle, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 모든 자전거 조회
    @GetMapping("/bikes")
    public ResponseEntity<List<Bike>> getAllBicycles() {
        List<Bike> bikes = bikeRepository.findAll();
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }

    // 특정 ID의 자전거 조회
    @GetMapping("/bikes/{bikeId}")
    public ResponseEntity<Bike> getBicycleById(@PathVariable int bikeId) {
        Optional<Bike> bike = bikeRepository.findById(bikeId);
        return bike.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 자전거 소유자의 정보 조회
//    @GetMapping("/{bikeId}/{id}")
//    public ResponseEntity<User> getOwnerByBicycleId(@PathVariable int bikeId) {
//        Optional<Bike> bicycle = bikeRepository.findById(bikeId);
//        return bicycle.map(value -> new ResponseEntity<>(value.getUser(), HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    // 자전거 수정
    @PutMapping("/bikes/{bikeId}")
    public ResponseEntity<Bike> updateBicycle(@PathVariable int bikeId, @RequestBody Bike updatedBicycle) {
        Optional<Bike> existingBicycle = bikeRepository.findById(bikeId);
        if (existingBicycle.isPresent()) {
            updatedBicycle.setId(bikeId);
            Bike savedBicycle = bikeRepository.save(updatedBicycle);
            return new ResponseEntity<>(savedBicycle, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 자전거 삭제
    @DeleteMapping("/bikes/{bikeId}")
    public ResponseEntity<Void> deleteBicycle(@PathVariable int bikeId) {
        Optional<Bike> bicycle = bikeRepository.findById(bikeId);
        if (bicycle.isPresent()) {
            bikeRepository.deleteById(bikeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
