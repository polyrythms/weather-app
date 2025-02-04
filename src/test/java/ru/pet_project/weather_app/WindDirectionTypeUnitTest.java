package ru.pet_project.weather_app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.pet_project.weather_app.model.WindDirectionType;

public class WindDirectionTypeUnitTest {
    @Test
    public void getTypeByDegree_shouldReturnN_while0degree() {
        Assertions.assertEquals(WindDirectionType.N, WindDirectionType.getTypeByDegree(2));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while382degree() {
        Assertions.assertEquals(WindDirectionType.N, WindDirectionType.getTypeByDegree(382));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while360degree() {
        Assertions.assertEquals(WindDirectionType.N, WindDirectionType.getTypeByDegree(360));
    }

    @Test
    public void getTypeByDegree_shouldReturnN_while22degree() {
        Assertions.assertEquals(WindDirectionType.N, WindDirectionType.getTypeByDegree(22));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while340degree() {
        Assertions.assertEquals(WindDirectionType.N, WindDirectionType.getTypeByDegree(340));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while45degree() {
        Assertions.assertEquals(WindDirectionType.NE, WindDirectionType.getTypeByDegree(45));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while180degree() {
        Assertions.assertEquals(WindDirectionType.S, WindDirectionType.getTypeByDegree(180));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while202degree() {
        Assertions.assertEquals(WindDirectionType.S, WindDirectionType.getTypeByDegree(202));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while158degree() {
        Assertions.assertEquals(WindDirectionType.S, WindDirectionType.getTypeByDegree(158));
    }
    @Test
    public void getTypeByDegree_shouldReturnN_while181degree() {
        Assertions.assertEquals(WindDirectionType.S, WindDirectionType.getTypeByDegree(181));
    }

}
