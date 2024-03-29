# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
import grpc

import PCSBasedComponentParameter_pb2 as PCSBasedComponentParameter__pb2


class PCSBasedOptimizerServiceStub(object):
  # missing associated documentation comment in .proto file
  pass

  def __init__(self, channel):
    """Constructor.

    Args:
      channel: A grpc.Channel.
    """
    self.Evaluate = channel.unary_unary(
        '/pcsbasedoptimization.PCSBasedOptimizerService/Evaluate',
        request_serializer=PCSBasedComponentParameter__pb2.PCSBasedComponentProto.SerializeToString,
        response_deserializer=PCSBasedComponentParameter__pb2.PCSBasedEvaluationResponseProto.FromString,
        )


class PCSBasedOptimizerServiceServicer(object):
  # missing associated documentation comment in .proto file
  pass

  def Evaluate(self, request, context):
    # missing associated documentation comment in .proto file
    pass
    context.set_code(grpc.StatusCode.UNIMPLEMENTED)
    context.set_details('Method not implemented!')
    raise NotImplementedError('Method not implemented!')


def add_PCSBasedOptimizerServiceServicer_to_server(servicer, server):
  rpc_method_handlers = {
      'Evaluate': grpc.unary_unary_rpc_method_handler(
          servicer.Evaluate,
          request_deserializer=PCSBasedComponentParameter__pb2.PCSBasedComponentProto.FromString,
          response_serializer=PCSBasedComponentParameter__pb2.PCSBasedEvaluationResponseProto.SerializeToString,
      ),
  }
  generic_handler = grpc.method_handlers_generic_handler(
      'pcsbasedoptimization.PCSBasedOptimizerService', rpc_method_handlers)
  server.add_generic_rpc_handlers((generic_handler,))
