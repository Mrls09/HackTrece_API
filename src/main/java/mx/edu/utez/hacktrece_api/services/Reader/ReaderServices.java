package mx.edu.utez.hacktrece_api.services.Reader;

import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronic;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderElectronicRepository;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotal;
import mx.edu.utez.hacktrece_api.model.Reader.ReaderTotalRepository;
import mx.edu.utez.hacktrece_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReaderServices {
    @Autowired
    private ReaderElectronicRepository electronicRepository;
    @Autowired
    private ReaderTotalRepository totalRepository;

    public ReaderTotal saveReaderTotal(ReaderTotal readerTotal) {
        return totalRepository.save(readerTotal);
    }
    public ReaderElectronic saveReaderElectronic(ReaderElectronic readerElectronic) {
        return electronicRepository.save(readerElectronic);
    }

}
