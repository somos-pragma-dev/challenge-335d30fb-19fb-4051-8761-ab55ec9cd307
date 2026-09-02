# Prompt para Mejorar el Codigo Base

Copia y pega el siguiente contenido completo en un asistente de IA (Claude, ChatGPT, etc.)
para obtener un ZIP con el proyecto corregido y listo para compilar.

---

```
Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación o descripciones sin código, genera los archivos
correspondientes sin aplicar análisis de compilación
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:
// === ARCHIVO: src/main/java/com/fintech/api/TransactionController.java ===
package com.fintech.api;

import com.fintech.domain.Transaction;
import com.fintech.domain.TransactionDto;
import com.fintech.infrastructure.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Operation(summary = "Crea una nueva transacción")
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = convertToEntity(transactionDto);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return new ResponseEntity<>(convertToDto(savedTransaction), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todas las transacciones")
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions.stream().map(this::convertToDto).toList(), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una transacción por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(value -> new ResponseEntity<>(convertToDto(value), HttpStatus.OK))
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Actualiza una transacción por ID")
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction updatedTransaction = convertToEntity(transactionDto);
            updatedTransaction.setId(id);
            return new ResponseEntity<>(convertToDto(transactionRepository.save(updatedTransaction)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Elimina una transacción por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            transactionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Transaction convertToEntity(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setOperationNumber(transactionDto.getOperationNumber());
        transaction.setSourceChannel(transactionDto.getSourceChannel());
        transaction.setAmount(transactionDto.getAmount());
        return transaction;
    }

    private TransactionDto convertToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOperationNumber(transaction.getOperationNumber());
        transactionDto.setSourceChannel(transaction.getSourceChannel());
        transactionDto.setAmount(transaction.getAmount());
        return transactionDto;
    }
}

// === ARCHIVO: src/main/java/com/fintech/domain/Transaction.java ===
package com.fintech.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operationNumber;

    @Column(nullable = false)
    private String sourceChannel;

    @Column(nullable = false)
    private BigDecimal amount;

    public Transaction() {}

    public Transaction(String operationNumber, String sourceChannel, BigDecimal amount) {
        this.operationNumber = operationNumber;
        this.sourceChannel = sourceChannel;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

// === ARCHIVO: src/main/java/com/fintech/infrastructure/TransactionRepository.java ===
package com.fintech.infrastructure;

import com.fintech.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {}

// === ARCHIVO: src/main/resources/application.properties ===
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

// === ARCHIVO: src/main/resources/db/migration/V1__initial_schema.sql ===
CREATE TABLE transaction (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation_number VARCHAR(255) NOT NULL,
    source_channel VARCHAR(255) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL
);

// === ARCHIVO: src/main/resources/openapi.yaml ===
openapi: 3.0.1
info:
  title: Transaction API
  version: 1.0.0
paths:
  /transactions:
    post:
      summary: Crea una nueva transacción
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TransactionDto'
      responses:
        '201':
          description: Transacción creada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TransactionDto'
    get:
      summary: Obtiene todas las transacciones
      responses:
        '200':
          description: Lista de transacciones
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/TransactionDto'
  /transactions/{id}:
    get:
      summary: Obtiene una transacción por ID
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
      responses:
        '200':
          description: Transacción encontrada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TransactionDto'
        '404':
          description: Transacción no encontrada
    put:
      summary: Actualiza una transacción por ID
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/TransactionDto'
      responses:
        '200':
          description: Transacción actualizada
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/TransactionDto'
        '404':
          description: Transacción no encontrada
    delete:
      summary: Elimina una transacción por ID
      parameters:
        - name: id
          in: path
          required: true
          schema:
            type: integer
      responses:
        '204':
          description: Transacción eliminada
        '404':
          description: Transacción no encontrada
components:
  schemas:
    TransactionDto:
      type: object
      properties:
        operationNumber:
          type: string
        sourceChannel:
          type: string
        amount:
          type: number
          format: double

// === ARCHIVO: src/test/java/com/fintech/api/TransactionControllerTest.java ===
package com.fintech.api;

import com.fintech.domain.TransactionDto;
import com.fintech.infrastructure.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionRepository transactionRepository;

    @Test
    void createTransaction() throws Exception {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOperationNumber("12345");
        transactionDto.setSourceChannel("WEB");
        transactionDto.setAmount(new BigDecimal("100.00"));

        Mockito.when(transactionRepository.save(any())).thenReturn(new com.fintech.domain.Transaction());

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content("{\"operationNumber\":\"12345\",\"sourceChannel\":\"WEB\",\"amount\":100.00}"))
               .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}

// === ARCHIVO: src/test/java/com/fintech/domain/TransactionTest.java ===
package com.fintech.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testTransaction() {
        Transaction transaction = new Transaction("12345", "WEB", new BigDecimal("100.00"));
        assertEquals("12345", transaction.getOperationNumber());
        assertEquals("WEB", transaction.getSourceChannel());
        assertEquals(new BigDecimal("100.00"), transaction.getAmount());
    }
}

// === ARCHIVO: src/test/java/com/fintech/infrastructure/TransactionRepositoryTest.java ===
package com.fintech.infrastructure;

import com.fintech.domain.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.Optional;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void testFindById() {
        Transaction transaction = new Transaction("12345", "WEB", new BigDecimal("100.00"));
        entityManager.persist(transaction);
        entityManager.flush();

        Optional<Transaction> found = transactionRepository.findById(transaction.getId());
        assertTrue(found.isPresent());
        assertEquals("12345", found.get().getOperationNumber());
    }
}

```
