from flask import Flask, render_template
from flask import request
import json
import requests
import hashlib as hasher
import datetime as date
import copy
node = Flask(__name__)

class Block:
  def __init__(self, index, timestamp, data, previous_hash):
    self.index = index
    self.timestamp = timestamp
    self.data = data
    self.previous_hash = previous_hash
    self.hash = self.hash_block()
  
  def hash_block(self):
    sha = hasher.sha256()
    sha.update(str(self.index) + str(self.timestamp) + str(self.data) + str(self.previous_hash))
    return sha.hexdigest()

@node.route('/mine', methods = ['GET'])
def mine():
  last_block = blockchain[len(blockchain) - 1]
  last_proof = last_block.data['proof-of-work']
  proof = proof_of_work(last_proof)
  this_nodes_transactions.append(
    { "from": "network", "to": miner_address, "amount": 1 }
  )
  new_block_data = {
    "proof-of-work": proof,
    "transactions": list(this_nodes_transactions)
  }
  new_block_index = last_block.index + 1
  new_block_timestamp = this_timestamp = date.datetime.now()
  last_block_hash = last_block.hash
  this_nodes_transactions[:] = []
  mined_block = Block(
    new_block_index,
    new_block_timestamp,
    new_block_data,
    last_block_hash
  )
  blockchain.append(mined_block)
  
  block = {"index": new_block_index, "timestamp": str(new_block_timestamp), "data": new_block_data, "hash": last_block_hash}

  return render_template('mine.html', block=block)
  


def create_genesis_block():
  return Block(0, date.datetime.now(), {
    "proof-of-work": 9,
    "transactions": None
  }, "0")

miner_address = "q3nf394hjg-random-miner-address-34nf3i4nflkn3oi"
blockchain = []
blockchain.append(create_genesis_block())
this_nodes_transactions = []
peer_nodes = []
mining = True

@node.route('/txion', methods=['POST'])
def transaction():
  new_txion = request.get_json()
  this_nodes_transactions.append(new_txion)
  print "New transaction"
  print "FROM: {}".format(new_txion['from'].encode('ascii','replace'))
  print "TO: {}".format(new_txion['to'].encode('ascii','replace'))
  print "AMOUNT: {}\n".format(new_txion['amount'])
  last_block = blockchain[len(blockchain) - 1]
  last_proof = last_block.data['proof-of-work']
  proof = proof_of_work(last_proof)
  this_nodes_transactions.append(
    { "from": "network", "to": miner_address, "amount": 1 }
  )
  new_block_data = {
    "proof-of-work": proof,
    "transactions": list(this_nodes_transactions)
  }
  new_block_index = last_block.index + 1
  new_block_timestamp = this_timestamp = date.datetime.now()
  last_block_hash = last_block.hash
  this_nodes_transactions[:] = []
  mined_block = Block(
    new_block_index,
    new_block_timestamp,
    new_block_data,
    last_block_hash
  )
  blockchain.append(mined_block)
  
  block = {"index": new_block_index, "timestamp": str(new_block_timestamp), "data": new_block_data, "hash": last_block_hash}
  print(block)
  
  return 'Transaction Successful'

@node.route('/req', methods=['POST'])
def pp():
    return render_template('main.html', a=aa)

@node.route('/blocks', methods=['GET'])
def get_blocks():
  chain_to_send = copy.copy(blockchain)
  for i in range(len(chain_to_send)):
    block = chain_to_send[i]
    block_index = str(block.index)
    block_timestamp = str(block.timestamp)
    block_data = str(block.data)
    block_hash = block.hash
    data = json.loads(json.dumps(block_data))
    chain_to_send[i] = {
      "index": block_index,
      "timestamp": block_timestamp,
      "data": data,
      "hash": block_hash
    }
  return render_template('blocks.html', chain=chain_to_send)


def proof_of_work(last_proof):
  incrementor = last_proof + 1
  while not (incrementor % 9 == 0 and incrementor % last_proof == 0):
    incrementor += 1
  return incrementor

@node.route('/gui', methods = ['GET'])
def gui():
    return None

if __name__ == '__main__':
    node.run(debug=True, host='0.0.0.0')

