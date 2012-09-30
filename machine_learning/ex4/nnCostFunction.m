function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices.
%
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);

% You need to return the following variables correctly
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

% ================================== PART 1 ==================================
[hy, z1, z2] = hyp(X, Theta1, Theta2){1:end};
yy = mapv(y, num_labels);

J = -1/m*sum(sum(yy.*log(hy) + (1-yy).*log(1-hy))) + lambda/(2*m)*(sqsummat(Theta1, Theta2));

% ================================== PART 2 ==================================

delta_3 = hy - yy;
delta_2 = (delta_3*Theta2(:, 2:end)).*sigmoidGradient(z1);

Theta1_grad = 1/m*(Theta1_grad + delta_2'*aug(X)) + lambda/m*Theta1;
Theta1_grad(:,1) -= lambda/m*Theta1(:,1);
Theta2_grad = 1/m*(Theta2_grad + delta_3'*aug(sigmoid(z1))) + lambda/m*Theta2;
Theta2_grad(:,1) -= lambda/m*Theta2(:,1);
% -------------------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];

end

% ===================== HELPERS ============================================
% The following function gets variable arguments. The first must be the input layer vector
% and the second till the end the Theta's i.e. the mapping matrices for the hidden layers.
% Returns the vector of the hypothesis.

function h = hyp(varargin)
    if (nargin == 2)
        if (iscell(varargin{1}))
            z = aug(varargin{1}{1:1})*varargin{2}';
            h = {sigmoid(z), varargin{1}{2:end}, z};
        else
            z = aug(varargin{1})*varargin{2}';
            h = {sigmoid(z), z};
        end
    else
        h = hyp(hyp(varargin{1}, varargin{2:(nargin-1)}), varargin{nargin});
    end
end

function vec = mapv(ar, n)
    vec = zeros(rows(ar), n);
    in = zeros(1, n);
    for i = 1:rows(ar)
        vec(i,:) = in;
        vec(i,ar(i)) = 1;
    end
end

function mat = aug(x)
    mat = [ones(rows(x), 1) x];
end

function num = sqsummat(varargin)
    if (nargin == 0)
        num = 0;
    else
        num = sum(sum(varargin{1}(:,2:end).*varargin{1}(:,2:end))) + sqsummat(varargin{2:nargin});
    end
end

