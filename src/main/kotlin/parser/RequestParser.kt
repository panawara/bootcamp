package parser

import model.Request
import java.io.File

abstract class RequestParser: (File) -> List<Request>