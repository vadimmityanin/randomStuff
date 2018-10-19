package my.app.onetomany.bidirectional;

import java.util.UUID;

public interface HomeRepositoryCustom {

    Home getIDZ(Long id);

    Home getByCode(UUID code);
}
