package com.utc.rooming_house.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utc.rooming_house.dto.BillDTO;
import com.utc.rooming_house.dto.RoomServiceDTO;
import com.utc.rooming_house.entity.Bill;
import com.utc.rooming_house.entity.BillDetail;
import com.utc.rooming_house.entity.Contract;
import com.utc.rooming_house.entity.Room;
import com.utc.rooming_house.entity.Room_Service;
import com.utc.rooming_house.repository.BillDetailRepo;
import com.utc.rooming_house.repository.BillRepo;
import com.utc.rooming_house.repository.ContractRepo;
import com.utc.rooming_house.repository.RoomRepo;
import com.utc.rooming_house.repository.RoomServiceRepo;
import com.utc.rooming_house.utils.Utils;

public interface BillService {
	BillDTO create(BillDTO billDTO);
}
@Service
class BillServiceImpl implements BillService {
	
	@Autowired
	BillRepo billRepo;
	
	@Autowired
	RoomService roomService;
	@Autowired
	RoomServiceRepo roomServiceRepo;
	@Autowired
	BillDetailRepo billDetailRepo;
	@Autowired
	ContractRepo contractRepo;
	
	@Override
	public BillDTO create(BillDTO billDTO) {
		ModelMapper mapper = new ModelMapper();
		Bill bill = mapper.map(billDTO, Bill.class);
		bill.setBillId(UUID.randomUUID().toString());
		Room room = roomService.get(billDTO.getRoom().getRoomId());
		bill.setRoom(room);
		bill.setBillStatus("UN_PAID");
		bill.setDueDay(Utils.getEndDate2(new Date(), 15));
		billRepo.save(bill);
		
		Optional<Contract> contract = contractRepo.findByRoom(room);
		BillDetail billD = new BillDetail();
		billD.setBillDetailDes("Tiền phòng");
		billD.setBillDetailNumber(1);
		billD.setValue(contract.get().getContractRent());
		billD.setBill(bill);
		billDetailRepo.save(billD);
		Long value = billD.getValue();
		
		List<Room_Service> roomSers = roomServiceRepo.findByRoom(room);
		for (Room_Service room_Ser : roomSers) {
			BillDetail billDetail = new BillDetail();
			billDetail.setBillDetailDes(room_Ser.getServices().getServiceName());
			billDetail.setBillDetailNumber(room_Ser.getServiceNumber());
			billDetail.setValue(room_Ser.getServiceValue());
			billDetail.setBill(bill);
			value += billDetail.getValue();
			billDetailRepo.save(billDetail);
		}
		bill.setBillValue(value);
		bill.setTotalValue(value- bill.getBillReduce());
		billRepo.save(bill);
		return billDTO;
	}
	
}